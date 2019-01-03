package com.rdnational.syntechx.syntech_x;

import java.util.List;

public class EventsFragmentItems {
    private int event_id;
    private String event_logo;
    private String Description;
    private String Participants;
    private String event_head;
    private String event_name;
    private String event_head_phone;
    private String event_head_img;
    private String venue;
    List<String> event_rules;
    List<String> entry_fees;
    private String event_color;


    public EventsFragmentItems(){

    }
    public EventsFragmentItems(int event_id,String eventImages, String event_name,String Description,String Participaints,String event_head,String event_head_phone,String event_head_img,List<String> event_rules,List<String> entry_fees,String event_color,String venue) {
        this.event_id = event_id;
        this.event_logo = eventImages;
        this.event_name = event_name;
        this.Description = Description;
        this.Participants = Participaints;
        this.event_head = event_head;
        this.event_head_phone = event_head_phone;
        this.event_head_img = event_head_img;
        this.event_rules = event_rules;
        this.entry_fees = entry_fees;
        this.event_color = event_color;
        this.venue = venue;
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

    public void setEvent_color(String event_color) {
        this.event_color = event_color;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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

    public String getEvent_color() {
        return event_color;
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getVenue() {
        return venue;
    }

    public List<String> getEntry_fees() {
        return entry_fees;
    }

}
