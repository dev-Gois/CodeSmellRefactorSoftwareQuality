package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial{
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial(){
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial(){
        if(studyMaterial == null){
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref){
        references.add(ref);
    }

    List<Reference> getReferences(){
        return references;
    }

    public List<Reference> getTypeReference(Reference type){
        List<Reference> response = new ArrayList<>();
        for(Reference reference : references){
            if(reference.getClass() == type.getClass()){
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text){
        List<String> response = new ArrayList<>();
        for(Reference reference : references){
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeReferenceCountMap();
        countReferencesByType(response);
        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeReferenceCountMap() {
        Map<String, Integer> referenceMap = new HashMap<>();
        referenceMap.put("Audio References", 0);
        referenceMap.put("Video References", 0);
        referenceMap.put("Text References", 0);
        return referenceMap;
    }

    private void countReferencesByType(Map<String, Integer> referenceMap) {
        for (Reference reference : references) {
            if (reference.getClass() == AudioReference.class) {
                handleAudioReference(referenceMap);
            } else if (reference.getClass() == VideoReference.class) {
                handleVideoReference(referenceMap, (VideoReference) reference);
            } else if (reference.getClass() == TextReference.class) {
                handleTextReference(referenceMap, (TextReference) reference);
            }
        }
    }

    private void handleAudioReference(Map<String, Integer> referenceMap) {
        incrementCount(referenceMap, "Audio References");
    }

    private void handleVideoReference(Map<String, Integer> referenceMap, VideoReference reference) {
        if (reference.handleStreamAvailability()) {
            incrementCount(referenceMap, "Video References");
        }
    }

    private void handleTextReference(Map<String, Integer> referenceMap, TextReference reference) {
        if (reference.handleTextAccess()) {
            incrementCount(referenceMap, "Text References");
        }
    }

    private void incrementCount(Map<String, Integer> map, String key) {
        map.put(key, map.get(key) + 1);
    }


}
