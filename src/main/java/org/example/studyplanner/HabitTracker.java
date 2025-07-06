package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return new ArrayList<>(this.tracker.keySet());
    }

    // -----------------------------------------------------------------------------------
    // Builder-like Temporary State for Fluent Habit Creation
    // -----------------------------------------------------------------------------------
    private String tempName;
    private String tempMotivation;
    private LocalTime tempDedicationTime;
    private LocalDateTime tempStartDate;
    private Boolean tempIsConcluded;

    public HabitTracker startNewHabit(String name, String motivation) {
        this.tempName = name;
        this.tempMotivation = motivation;
        return this;
    }

    public HabitTracker withDedicationTime(int hours, int minutes) {
        this.tempDedicationTime = LocalTime.of(hours, minutes);
        return this;
    }

    public HabitTracker startingOn(int year, int month, int day, int hour, int minute, int seconds) {
        this.tempStartDate = LocalDateTime.of(year, month, day, hour, minute, seconds);
        return this;
    }

    public HabitTracker markConcluded(boolean isConcluded) {
        this.tempIsConcluded = isConcluded;
        return this;
    }

    public int save() {
        Habit habit = new Habit(
                tempName,
                tempMotivation,
                tempDedicationTime,
                this.nextId,
                tempStartDate,
                tempIsConcluded
        );

        this.habits.add(habit);
        this.tracker.put(nextId, new ArrayList<>());
        int createdId = nextId;
        nextId++;

        resetTempFields();
        return createdId;
    }

    private void resetTempFields() {
        tempName = null;
        tempMotivation = null;
        tempDedicationTime = null;
        tempStartDate = null;
        tempIsConcluded = null;
    }

    // -----------------------------------------------------------------------------------
    // Other HabitTracker Methods
    // -----------------------------------------------------------------------------------

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded){
        return this.startNewHabit(stringProperties.get(0), stringProperties.get(1))
                .withDedicationTime(intProperties.get(1), intProperties.get(0)) // hours, minutes
                .startingOn(intProperties.get(2), intProperties.get(3), intProperties.get(4),
                        intProperties.get(5), intProperties.get(6), intProperties.get(7))
                .markConcluded(isConcluded)
                .save();
    }

    public int addHabit(String name, String motivation) {
        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        this.tracker.put(nextId, new ArrayList<>());
        return nextId++;
    }

    public void addHabitRecord(Integer id){
        if (tracker.containsKey(id)) {
            tracker.get(id).add(LocalDateTime.now());
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.getOrDefault(id, new ArrayList<>());
    }

    public List<String> searchInHabits(String search){
        List<String> result = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) ||
                    habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                result.add(habit.toString());
            }
        }
        return result;
    }
}
