package com.rdnational.syntechx.syntech_x;

import java.util.ArrayList;

public class GallerySectionDataModel {

    private String headerTitle;
    private ArrayList<GalleryItems> allItemInSection;

    public GallerySectionDataModel(){

    }

    public GallerySectionDataModel(String headerTitle, ArrayList<GalleryItems> allItemInSection) {
        this.headerTitle = headerTitle;
        this.allItemInSection = allItemInSection;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public void setAllItemInSection(ArrayList<GalleryItems> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public ArrayList<GalleryItems> getAllItemInSection() {
        return allItemInSection;
    }
}
