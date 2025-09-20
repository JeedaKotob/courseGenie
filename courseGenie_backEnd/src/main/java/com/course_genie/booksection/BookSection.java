package com.course_genie.booksection;

import com.course_genie.book.Book;
import com.course_genie.section.Section;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"book", "section"})
@EqualsAndHashCode(exclude = {"book", "section"})
public class BookSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    private boolean requiredReading;
    private boolean suggestedReading;
}