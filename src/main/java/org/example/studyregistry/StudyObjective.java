package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration; // Duration per day in hours, for example
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    // --- Getters ---

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    // --- Setters (only where truly needed) ---

    public void setDescription(String description) {
        this.description = description;
    }

    // --- Behavioral methods ---

    public void incrementPracticedDays() {
        if (practicedDays == null) {
            practicedDays = 1;
        } else {
            practicedDays++;
        }
    }

    public boolean isActiveObjective() {
        return this.isActive;
    }

    /**
     * Calculate total study time so far (practicedDays * duration per day).
     * Returns 0 if duration or practicedDays is null.
     */
    public double totalStudyHours() {
        if (duration == null || practicedDays == null) {
            return 0;
        }
        return duration * practicedDays;
    }

    /**
     * Return a summary for display, prefer the one-line objective if available.
     */
    public String summarizeObjective() {
        return objectiveInOneLine != null ? objectiveInOneLine : title;
    }

    // --- Smaller setter methods to avoid long parameter lists ---

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(String title, String description, String topic, String objectiveInOneLine,
                                     String objectiveFullDescription, String motivation) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration) {
        this.practicedDays = practicedDays;
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    // Adapter method, calls smaller setters internally
    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration,
                                         boolean isActive) {
        handleSetRegistry(intProperties.get(0), stringProperties.get(0), intProperties.get(1), isActive);
        handleSetTextualInfo(stringProperties.get(1), stringProperties.get(2), stringProperties.get(3), stringProperties.get(4),
                stringProperties.get(5), stringProperties.get(6));
        handleSetTime(intProperties.get(2), intProperties.get(3), intProperties.get(4), intProperties.get(5), duration);
        return intProperties.get(0);
    }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description
                + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "")
                + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "")
                + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }
}
