package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public StudyPlan addStep(String step) {
        this.steps.add(step);
        return this;
    }

    public StudyPlan addSteps(List<String> stepsToAdd) {
        this.steps.addAll(stepsToAdd);
        return this;
    }

    public StudyPlan setNumberOfSteps(int numberOfSteps) {
        this.steps.add("Number of steps: " + numberOfSteps);
        return this;
    }

    public StudyPlan setImportance(boolean isImportant) {
        this.steps.add("Is it important to you? " + isImportant);
        return this;
    }

    public StudyPlan setDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.steps.add(startDate.format(formatter));
        this.steps.add(endDate.format(formatter));
        return this;
    }

    /**
     * Assigns all steps and details from a map of step keys and values,
     * plus additional parameters to avoid a long parameter list.
     */
    public void assignSteps(Map<String, String> stepsMap, int numberOfSteps, boolean isImportant,
                            LocalDateTime startDate, LocalDateTime endDate) {
        this.steps.clear();

        // Define the order of keys to ensure consistent order of steps
        String[] stepOrder = {
                "firstStep", "resetStudyMechanism", "consistentStep", "seasonalSteps", "basicSteps",
                "mainObjectiveTitle", "mainGoalTitle", "mainMaterialTopic", "mainTask"
        };

        for (String key : stepOrder) {
            String value = stepsMap.get(key);
            if (value != null) {
                this.addStep(value);
            }
        }

        this.setNumberOfSteps(numberOfSteps)
                .setImportance(isImportant)
                .setDateRange(startDate, endDate);
    }

    /**
     * Helper method to use previous List<String> interface for backward compatibility.
     * Converts list to map and calls assignSteps.
     */
    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant,
                                  LocalDateTime startDate, LocalDateTime endDate) {
        if (stringProperties.size() < 9) {
            throw new IllegalArgumentException("Expected at least 9 string properties");
        }
        Map<String, String> stepsMap = Map.of(
                "firstStep", stringProperties.get(0),
                "resetStudyMechanism", stringProperties.get(1),
                "consistentStep", stringProperties.get(2),
                "seasonalSteps", stringProperties.get(3),
                "basicSteps", stringProperties.get(4),
                "mainObjectiveTitle", stringProperties.get(5),
                "mainGoalTitle", stringProperties.get(6),
                "mainMaterialTopic", stringProperties.get(7),
                "mainTask", stringProperties.get(8)
        );

        assignSteps(stepsMap, numberOfSteps, isImportant, startDate, endDate);
    }
}
