package com.rdnational.syntechx.syntech_x;

import java.util.ArrayList;

public class SectionDataModel {

    private String headerTitle;
    private ArrayList<HomeSingleItemsModel> allItemInSection;

    public SectionDataModel(){

    }

    public SectionDataModel(String headerTitle, ArrayList<HomeSingleItemsModel> allItemInSection) {
        this.headerTitle = headerTitle;
        this.allItemInSection = allItemInSection;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public void setAllItemInSection(ArrayList<HomeSingleItemsModel> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public ArrayList<HomeSingleItemsModel> getAllItemInSection() {
        return allItemInSection;
    }
}
