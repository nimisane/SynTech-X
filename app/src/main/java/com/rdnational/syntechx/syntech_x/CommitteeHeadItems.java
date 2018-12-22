package com.rdnational.syntechx.syntech_x;

public class CommitteeHeadItems {

    private String committee_head_name;
    private String committee_head_image;

    public CommitteeHeadItems(){

    }

    public CommitteeHeadItems(String committee_head_name, String committee_head_image) {
        this.committee_head_name = committee_head_name;
        this.committee_head_image = committee_head_image;
    }

    public void setCommittee_head_name(String committee_head_name) {
        this.committee_head_name = committee_head_name;
    }

    public void setCommittee_head_image(String committee_head_image) {
        this.committee_head_image = committee_head_image;
    }

    public String getCommittee_head_name() {
        return committee_head_name;
    }

    public String getCommittee_head_image() {
        return committee_head_image;
    }
}
