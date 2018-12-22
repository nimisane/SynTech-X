package com.rdnational.syntechx.syntech_x;

public class CommitteeNameItems {
    private String committeeName;
    private String committeePath;
    private String committeeImage;
    private String committeePosition;

    public CommitteeNameItems(){

    }

    public CommitteeNameItems(String committeeName, String committeePath, String committeeImage,String committeePosition) {
        this.committeeName = committeeName;
        this.committeePath = committeePath;
        this.committeeImage = committeeImage;
        this.committeePosition = committeePosition;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public void setCommitteePath(String committeePath) {
        this.committeePath = committeePath;
    }

    public void setCommitteeImage(String committeeImage) {
        this.committeeImage = committeeImage;
    }

    public void setCommitteePosition(String committeePosition) {
        this.committeePosition = committeePosition;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public String getCommitteePath() {
        return committeePath;
    }

    public String getCommitteeImage() {
        return committeeImage;
    }

    public String getCommitteePosition() {
        return committeePosition;
    }
}
