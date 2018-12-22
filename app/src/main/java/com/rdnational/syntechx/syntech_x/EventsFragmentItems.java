package com.rdnational.syntechx.syntech_x;

import java.util.List;

public class EventsFragmentItems {
    private String event_logo;
    private String Description;
    private String Participants;
    private String event_head;
    private String event_name;
    private String event_head_phone;
    private String event_head_img;
    List<String> event_rules;


    public EventsFragmentItems(){

    }
    public EventsFragmentItems(String eventImages, String event_name,String Description,String Participaints,String event_head,String event_head_phone,String event_head_img,List<String> event_rules) {
        this.event_logo = eventImages;
        this.event_name = event_name;
        this.Description = Description;
        this.Participants = Participaints;
        this.event_head = event_head;
        this.event_head_phone = event_head_phone;
        this.event_head_img = event_head_img;
        this.event_rules = event_rules;
    }

    public List<String> getEvent_rules() {
        return event_rules;
    }

    public void setEvent_head_phone(String event_head_phone) {
        this.event_head_phone = event_head_phone;
    }

    public void setEvent_head_img(String event_head_img) {
        this.event_head_img = event_head_img;
    }

    public void setEvent_logo(String event_logo) {
        this.event_logo = event_logo;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setParticipants(String participants) {
        Participants = participants;
    }

    public void setEvent_head(String event_head) {
        this.event_head = event_head;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_logo() {
        return event_logo;
    }

    public String getDescription() {
        return Description;
    }

    public String getParticipants() {
        return Participants;
    }

    public String getEvent_head() {
        return event_head;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_head_phone() {
        return event_head_phone;
    }

    public String getEvent_head_img() {
        return event_head_img;
    }
}
