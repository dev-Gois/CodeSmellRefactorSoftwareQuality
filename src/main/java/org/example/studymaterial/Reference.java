package org.example.studymaterial;

public abstract class Reference {

    // New private nested class holding all fields
     protected class ReferenceData {
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private boolean isDownloadable;
        private int rating;
        private String language;
        private int viewCount;
        private int downloadCount;
        private int shareCount;
    }

    private ReferenceData data = new ReferenceData();

    public Reference() {
    }

    public void setTitle(String title) {
        this.data.title = title;
    }

    public String getTitle() {
        return this.data.title;
    }

    public void setDescription(String description) {
        this.data.description = description;
    }

    public String getDescription() {
        return this.data.description;
    }

    public void setLink(String link) {
        this.data.link = link;
    }

    public String getLink() {
        return this.data.link;
    }

    public String getAccessRights() {
        return this.data.accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.data.accessRights = accessRights;
    }

    public String getLicense() {
        return this.data.license;
    }

    public void setLicense(String license) {
        this.data.license = license;
    }

    public boolean getIsDownloadable() {
        return this.data.isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.data.isDownloadable = downloadable;
    }

    public int getRating() {
        return this.data.rating;
    }

    public void setRating(int rating) {
        this.data.rating = rating;
    }

    public String getLanguage() {
        return this.data.language;
    }

    public void setLanguage(String language) {
        this.data.language = language;
    }

    public int getViewCount() {
        return this.data.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.data.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return this.data.downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.data.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return this.data.shareCount;
    }

    public void setShareCount(int shareCount) {
        this.data.shareCount = shareCount;
    }
}
