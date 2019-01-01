package com.rdnational.syntechx.syntech_x;

import java.util.List;

public class RulesItems {

    private String tabImage;
    List<String> tabInfo;

    public RulesItems(){

    }

    public RulesItems(String tabImage, List<String> tabInfo) {
        this.tabImage = tabImage;
        this.tabInfo = tabInfo;
    }

    public void setTabImage(String tabImage) {
        this.tabImage = tabImage;
    }

    public void setTabInfo(List<String> tabInfo) {
        this.tabInfo = tabInfo;
    }

    public String getTabImage() {
        return tabImage;
    }

    public List<String> getTabInfo() {
        return tabInfo;
    }
}
