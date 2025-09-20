package com.course_genie.book;

import com.course_genie.booksection.BookSectionRepository;
import com.course_genie.section.Section;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setUrl(book.getUrl());
        if (book.getBookSections() != null && !book.getBookSections().isEmpty()) {
            // For simplicity, take the first BookSection
            com.course_genie.booksection.BookSection bs = book.getBookSections().iterator().next();
            dto.setSectionIds(Collections.singleton(bs.getSection().getSectionId()));
            dto.setRequiredReading(bs.isRequiredReading());
            dto.setSuggestedReading(bs.isSuggestedReading());
        }
        return dto;
    }

    // Example conversion from DTO to Book entity, constructing BookSection associations for each Section
    public Book toEntity(BookDTO dto, Set<Section> sections) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setUrl(dto.getUrl());
        if (sections != null && !sections.isEmpty()) {
            Set<com.course_genie.booksection.BookSection> bookSections = sections.stream()
                    .map(section -> com.course_genie.booksection.BookSection.builder()
                            .book(book)
                            .section(section)
                            .requiredReading(dto.isRequiredReading())
                            .suggestedReading(dto.isSuggestedReading())
                            .build())
                    .collect(Collectors.toSet());
            book.setBookSections(bookSections);
        }
        return book;
    }
}