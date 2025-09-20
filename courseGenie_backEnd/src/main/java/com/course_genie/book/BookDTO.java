package com.course_genie.book;

import java.util.Set;

public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String url;
    // For simplicity, assume a singleton set of section IDs.
    private Set<Long> sectionIds;
    private boolean requiredReading;
    private boolean suggestedReading;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Set<Long> getSectionIds() {
        return sectionIds;
    }
    public void setSectionIds(Set<Long> sectionIds) {
        this.sectionIds = sectionIds;
    }
    public boolean isRequiredReading() {
        return requiredReading;
    }
    public void setRequiredReading(boolean requiredReading) {
        this.requiredReading = requiredReading;
    }
    public boolean isSuggestedReading() {
        return suggestedReading;
    }
    public void setSuggestedReading(boolean suggestedReading) {
        this.suggestedReading = suggestedReading;
    }
}