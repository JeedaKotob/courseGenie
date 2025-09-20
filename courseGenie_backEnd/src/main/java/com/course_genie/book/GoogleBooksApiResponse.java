package com.course_genie.book;

import java.util.List;

public class GoogleBooksApiResponse {
    private int totalItems;
    private List<Item> items;

    // Getters and setters
    public int getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private VolumeInfo volumeInfo;

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }
        public void setVolumeInfo(VolumeInfo volumeInfo) {
            this.volumeInfo = volumeInfo;
        }
    }

    public static class VolumeInfo {
        private String title;
        private List<IndustryIdentifier> industryIdentifiers;
        private ImageLinks imageLinks;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public List<IndustryIdentifier> getIndustryIdentifiers() {
            return industryIdentifiers;
        }
        public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
            this.industryIdentifiers = industryIdentifiers;
        }
        public ImageLinks getImageLinks() {
            return imageLinks;
        }
        public void setImageLinks(ImageLinks imageLinks) {
            this.imageLinks = imageLinks;
        }
    }

    public static class IndustryIdentifier {
        private String type;
        private String identifier;

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getIdentifier() {
            return identifier;
        }
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }

    public static class ImageLinks {
        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
