package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean done = false; // new field to represent state

    public ToDo(Integer id, String title, String description, int priority) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
    }

    public void markAsDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isHighPriority() {
        return this.priority >= 4; // define high priority as 4 or 5
    }

    public void updateDetails(String newTitle, String newDescription) {
        setTitle(newTitle);
        setDescription(newDescription);
    }

    public void incrementPriority() {
        if (priority < 5) {
            priority++;
        }
    }

    public void decrementPriority() {
        if (priority > 1) {
            priority--;
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    // Getters and setters with validation

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be positive and non-null");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
}
