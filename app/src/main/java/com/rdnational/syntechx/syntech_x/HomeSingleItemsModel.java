package com.rdnational.syntechx.syntech_x;

public class HomeSingleItemsModel {
    private String event_logo;
    private String event_name;
    private String event_color;

    public HomeSingleItemsModel(){

    }
    public HomeSingleItemsModel(String event_logo, String event_name, String event_color){
        this.event_logo = event_logo;
        this.event_name = event_name;
        this.event_color = event_color;
    }

    public void setEvent_logo(String event_logo) {
        this.event_logo = event_logo;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_color(String event_color) {
        this.event_color = event_color;
    }

    public String getEvent_color() {
        return event_color;
    }

    public String getEvent_logo() {
        return event_logo;
    }

    public String getEvent_name() {
        return event_name;
    }
}
