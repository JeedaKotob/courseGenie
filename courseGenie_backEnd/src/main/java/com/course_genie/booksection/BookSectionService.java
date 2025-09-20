package com.course_genie.booksection;

import com.course_genie.booksection.BookSectionRepository;
import com.course_genie.section.Section;
import com.course_genie.section.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSectionService {

    private final BookSectionRepository bookSectionRepository;
    private final SectionRepository sectionRepository;

    public BookSectionService(BookSectionRepository bookSectionRepository, SectionRepository sectionRepository) {
        this.bookSectionRepository = bookSectionRepository;
        this.sectionRepository = sectionRepository;
    }

    /**
     * Returns the list of BookSection mappings for required readings in the specified section.
     */
    public List<com.course_genie.booksection.BookSection> getRequiredReadingsForSection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with id " + sectionId));
        return bookSectionRepository.findBySectionAndRequiredReadingTrue(section);
    }

    /**
     * Returns the list of BookSection mappings for suggested readings in the specified section.
     */
    public List<com.course_genie.booksection.BookSection> getSuggestedReadingsForSection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with id " + sectionId));
        return bookSectionRepository.findBySectionAndSuggestedReadingTrue(section);
    }
}