package com.course_genie.section;

import com.course_genie.book.Book;
import com.course_genie.semester.Semester;

import com.course_genie.booksection.*;
import com.course_genie.course.Course;
import com.course_genie.user.User;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sectionId;

    private String code;
    private String term; // remove this
    private String class_number;
    private String schedule;
    private String room;
    @Column(length = 2000)
    private String teachingMethodology;
    private boolean configured;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private User professor;

    public Section(long sectionId) {
        this.sectionId = sectionId;
    }

    // Remove the outdated ManyToMany mapping for "books"
    // @ManyToMany(mappedBy = "sections")
    // private Set<Book> books;

    // Map to the join entity: BookSection
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<BookSection> bookSections;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    // Convenience method to get the Books associated with this Section from the join entity
    public Set<Book> getBooks() {
        if (bookSections == null) {
            return Collections.emptySet();
        }
        return bookSections.stream()
                .map(BookSection::getBook)
                .collect(Collectors.toSet());
    }

    // Convenience getter for section id
    public Long getId() {
        return sectionId;
    }

}