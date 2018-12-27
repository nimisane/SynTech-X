package com.rdnational.syntechx.syntech_x;

public class CommitteeHeadItems {

    private int head_id;
    private String committee_head_name;
    private String committee_head_image;

    public CommitteeHeadItems(){

    }

    public CommitteeHeadItems(int head_id,String committee_head_name, String committee_head_image) {
        this.head_id = head_id;
        this.committee_head_name = committee_head_name;
        this.committee_head_image = committee_head_image;
    }

    public void setHead_id(int head_id) {
        this.head_id = head_id;
    }

    public void setCommittee_head_name(String committee_head_name) {
        this.committee_head_name = committee_head_name;
    }

    public void setCommittee_head_image(String committee_head_image) {
        this.committee_head_image = committee_head_image;
    }

    public int getHead_id() {
        return head_id;
    }

    public String getCommittee_head_name() {
        return committee_head_name;
    }

    public String getCommittee_head_image() {
        return committee_head_image;
    }
}
