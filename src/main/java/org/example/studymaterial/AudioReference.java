package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    private AudioReference.AudioQuality audioQuality;

    public AudioReference(AudioReference.AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioReference.AudioQuality getAudioQuality() {
        return this.audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        if (quality == null) return null;
        quality = quality.toLowerCase();
        return
                quality.equals("low") ? AudioQuality.LOW :
                        quality.equals("medium") ? AudioQuality.MEDIUM :
                                quality.equals("high") ? AudioQuality.HIGH :
                                        quality.equals("very_high") ? AudioQuality.VERY_HIGH :
                                                null;
    }

    public void setAudioQuality(AudioReference.AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // Refactored methods to eliminate Long Parameter List
    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    public void editLicenseInfo(String accessRights, String license) {
        this.setAccessRights(accessRights);
        this.setLicense(license);
    }

    public void editLanguageInfo(String language) {
        this.setLanguage(language);
    }

    public void editAudioProperties(AudioReference.AudioQuality audioQuality, boolean isDownloadable) {
        this.setAudioQuality(audioQuality);
        this.setDownloadable(isDownloadable);
    }

    public void editMetrics(int rating, int viewCount, int shareCount) {
        this.setRating(rating);
        this.setViewCount(viewCount);
        this.setShareCount(shareCount);
    }

    // Still useful for batch updates from raw lists
    public void editAudioAdapter(List<String> properties,
                                 List<Integer> intProperties,
                                 AudioReference.AudioQuality audioQuality,
                                 boolean isDownloadable) {

        editStrings(properties);
        editInts(intProperties);
        editAudioProperties(audioQuality, isDownloadable);
    }

    private void editStrings(List<String> props) {
        this.setTitle(props.get(0));
        this.setDescription(props.get(1));
        this.setLink(props.get(2));
        this.setAccessRights(props.get(3));
        this.setLicense(props.get(4));
        this.setLanguage(props.get(5));
    }

    private void editInts(List<Integer> ints) {
        this.setRating(ints.get(0));
        this.setViewCount(ints.get(1));
        this.setShareCount(ints.get(2));
    }

    public static enum AudioQuality {
        LOW,
        MEDIUM,
        HIGH,
        VERY_HIGH
    }
}
