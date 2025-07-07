package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    // Temporary fields for setup
    private String planName;
    private String objectiveTitle;
    private String objectiveDescription;
    private String materialTopic;
    private String materialFormat;
    private String goal;
    private String reminderTitle;
    private String reminderDescription;
    private String mainTaskTitle;
    private String mainHabit;
    private String mainCardStudy;

    private StudyTaskManager(){
        this.registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    // Chained setters for each property
    public StudyTaskManager setPlanName(String planName) {
        this.planName = planName;
        return this;
    }

    public StudyTaskManager setObjectiveTitle(String objectiveTitle) {
        this.objectiveTitle = objectiveTitle;
        return this;
    }

    public StudyTaskManager setObjectiveDescription(String objectiveDescription) {
        this.objectiveDescription = objectiveDescription;
        return this;
    }

    public StudyTaskManager setMaterialTopic(String materialTopic) {
        this.materialTopic = materialTopic;
        return this;
    }

    public StudyTaskManager setMaterialFormat(String materialFormat) {
        this.materialFormat = materialFormat;
        return this;
    }

    public StudyTaskManager setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public StudyTaskManager setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
        return this;
    }

    public StudyTaskManager setReminderDescription(String reminderDescription) {
        this.reminderDescription = reminderDescription;
        return this;
    }

    public StudyTaskManager setMainTaskTitle(String mainTaskTitle) {
        this.mainTaskTitle = mainTaskTitle;
        return this;
    }

    public StudyTaskManager setMainHabit(String mainHabit) {
        this.mainHabit = mainHabit;
        return this;
    }

    public StudyTaskManager setMainCardStudy(String mainCardStudy) {
        this.mainCardStudy = mainCardStudy;
        return this;
    }

    // Call this to finalize and set the weekResponsibilities list
    public void applyWeekSetup() {
        this.weekResponsibilities = new ArrayList<>(Arrays.asList(
                planName,
                objectiveTitle,
                objectiveDescription,
                materialTopic,
                materialFormat,
                goal,
                reminderTitle,
                reminderDescription,
                mainTaskTitle,
                mainHabit,
                mainCardStudy
        ));
    }

    // Your other existing methods unchanged...

    public void handleSetUpWeek(List<String> stringProperties){
        setPlanName(stringProperties.get(0))
                .setObjectiveTitle(stringProperties.get(1))
                .setObjectiveDescription(stringProperties.get(2))
                .setMaterialTopic(stringProperties.get(3))
                .setMaterialFormat(stringProperties.get(4))
                .setGoal(stringProperties.get(5))
                .setReminderTitle(stringProperties.get(6))
                .setReminderDescription(stringProperties.get(7))
                .setMainTaskTitle(stringProperties.get(8))
                .setMainHabit(stringProperties.get(9))
                .setMainCardStudy(stringProperties.get(10))
                .applyWeekSetup();
    }

    public void addRegistry(Registry registry){
        registryList.add(registry);
    }
    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }
    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }
}
