package com.course_genie.booksection;

import com.course_genie.booksection.BookSection;
import com.course_genie.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSectionRepository extends JpaRepository<BookSection, Long> {
    List<BookSection> findBySectionAndRequiredReadingTrue(Section section);
    List<BookSection> findBySectionAndSuggestedReadingTrue(Section section);
}