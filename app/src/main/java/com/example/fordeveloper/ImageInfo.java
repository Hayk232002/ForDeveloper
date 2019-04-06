package com.example.fordeveloper;

public class ImageInfo {
    private String description;
    private String imageDownloadUri;
    private String name;
    private String uploadedBy;

    public ImageInfo() {
    }

    public ImageInfo(String description, String imageDownloadUri, String name, String uploadedBy) {
        this.description = description;
        this.imageDownloadUri = imageDownloadUri;
        this.name = name;
        this.uploadedBy = uploadedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageDownloadUri() {
        return imageDownloadUri;
    }

    public void setImageDownloadUri(String imageDownloadUri) {
        this.imageDownloadUri = imageDownloadUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
