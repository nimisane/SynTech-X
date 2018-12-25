package com.rdnational.syntechx.syntech_x;

public class GalleryItems {

    private String photo;
    private int p_id;

    public GalleryItems(){

    }

    public GalleryItems(String photo, int p_id) {
        this.photo = photo;
        this.p_id = p_id;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getPhoto() {
        return photo;
    }

    public int getP_id() {
        return p_id;
    }
}
