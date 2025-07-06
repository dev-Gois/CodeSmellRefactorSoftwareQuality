package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private DateInfo dateInfo;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.dateInfo = new DateInfo(date);
    }

    // Keep getters and setters as required

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public LocalDateTime getDate() {
        return dateInfo.getDate();
    }
    public void setDate(LocalDateTime date) {
        this.dateInfo.setDate(date);
    }

    // New behavior to add value and encapsulation
    public boolean isOverdue() {
        return dateInfo.isPast();
    }

    public String getSummary() {
        return title + " by " + author;
    }

    // Internal encapsulated date class
    private static class DateInfo {
        private LocalDateTime date;

        public DateInfo(LocalDateTime date) {
            this.date = date;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public boolean isPast() {
            return date.isBefore(LocalDateTime.now());
        }
    }
}
