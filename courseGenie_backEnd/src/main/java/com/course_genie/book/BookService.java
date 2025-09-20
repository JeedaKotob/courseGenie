package com.course_genie.book;

import com.course_genie.booksection.BookSection;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final SectionRepository sectionRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, SectionRepository sectionRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.sectionRepository = sectionRepository;
        this.bookMapper = bookMapper;
    }

    // Create a new book from a BookDTO
    public BookDTO createBook(BookDTO bookDTO) {
        Set<Section> sections = bookDTO.getSectionIds().stream()
                .map(id -> sectionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Section not found with id " + id)))
                .collect(Collectors.toSet());
        Book book = bookMapper.toEntity(bookDTO, sections);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    // Read all books
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Read a book by id
    public BookDTO getBookById(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return bookMapper.toDTO(book);
    }

    // Update a book by id using BookDTO
    public BookDTO updateBook(long bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Update the basic fields
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setUrl(bookDTO.getUrl());

        // Retrieve sections from the DTO's sectionIds
        Set<Section> sections = bookDTO.getSectionIds().stream()
                .map(id -> sectionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Section not found with id " + id)))
                .collect(Collectors.toSet());

        // Create a new set of BookSection associations from these sections
        Set<BookSection> bookSections = sections.stream()
                .map(section -> BookSection.builder()
                        .book(book)
                        .section(section)
                        .requiredReading(bookDTO.isRequiredReading())    // Use values from DTO or defaults
                        .suggestedReading(bookDTO.isSuggestedReading())
                        .build())
                .collect(Collectors.toSet());

        book.setBookSections(bookSections);

        return bookMapper.toDTO(bookRepository.save(book));
    }

    // Delete a book by id
    public void deleteBook(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    public BookDTO importBookByIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        // Replace with your actual Google Books API key or inject it from configuration.
        String googleBooksApiKey = "AIzaSyCxMyhKOvkgV1RovEVKkbJE06K-jFde8KI";
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + googleBooksApiKey;

        GoogleBooksApiResponse apiResponse = restTemplate.getForObject(apiUrl, GoogleBooksApiResponse.class);
        if (apiResponse == null || apiResponse.getTotalItems() == 0 ||
                apiResponse.getItems() == null || apiResponse.getItems().isEmpty()) {
            throw new RuntimeException("No book data found for ISBN: " + isbn);
        }

        // Extract data from the API response.
        GoogleBooksApiResponse.Item item = apiResponse.getItems().get(0);
        GoogleBooksApiResponse.VolumeInfo volumeInfo = item.getVolumeInfo();
        if (volumeInfo == null) {
            throw new RuntimeException("Book details are missing for ISBN: " + isbn);
        }

        // Retrieve the title, thumbnail, and resolve ISBN.
        String title = volumeInfo.getTitle();
        String thumbnailUrl = (volumeInfo.getImageLinks() != null)
                ? volumeInfo.getImageLinks().getThumbnail()
                : null;
        String resolvedIsbn = isbn;
        if (volumeInfo.getIndustryIdentifiers() != null && !volumeInfo.getIndustryIdentifiers().isEmpty()) {
            resolvedIsbn = volumeInfo.getIndustryIdentifiers().stream()
                    .filter(id -> "ISBN_13".equalsIgnoreCase(id.getType()))
                    .map(GoogleBooksApiResponse.IndustryIdentifier::getIdentifier)
                    .findFirst()
                    .orElse(isbn);
        }

        // Create a new Book entity using the extracted values.
        Book book = new Book();
        book.setIsbn(resolvedIsbn);
        book.setTitle(title);
        book.setUrl(thumbnailUrl);
        // Note: Do not set any mapping associations in this method.

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Transactional
    public Book updateMapping(Long bookId, Long sectionId, boolean mapped, boolean requiredReading, boolean suggestedReading) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));

        // Assume that book.getBookSections() returns a Set<BookSection>
        Set<BookSection> mappings = book.getBookSections();

        // Find existing mapping if any.
        Optional<BookSection> existingMapping = mappings.stream()
                .filter(bs -> bs.getSection().getSectionId() == sectionId) // using ==
                .findFirst();

        if (mapped) {
            if (existingMapping.isPresent()) {
                // Update the flags.
                BookSection bs = existingMapping.get();
                bs.setRequiredReading(requiredReading);
                bs.setSuggestedReading(suggestedReading);
            } else {
                // Create a new mapping.
                Section section = sectionRepository.findById(sectionId)
                        .orElseThrow(() -> new EntityNotFoundException("Section not found with id " + sectionId));
                BookSection newMapping = BookSection.builder()
                        .book(book)
                        .section(section)
                        .requiredReading(requiredReading)
                        .suggestedReading(suggestedReading)
                        .build();
                mappings.add(newMapping);
            }
        } else {
            // If not mapped, remove any existing mapping.
            existingMapping.ifPresent(mappings::remove);
        }

        book.setBookSections(mappings);
        return bookRepository.save(book);
    }

    public Optional<BookSection> getCurrentMapping(Long bookId, Long sectionId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));

        return book.getBookSections().stream()
                .filter(bs -> bs.getSection().getSectionId() == sectionId)
                .findFirst();
    }
}