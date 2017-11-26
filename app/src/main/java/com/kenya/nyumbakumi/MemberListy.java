package com.kenya.nyumbakumi;

/**
 * Created by Nderitu on 12/11/2017.
 */

public class MemberListy {
    private String name;
    private String phone;
    private String imageURL;

    public MemberListy(String name, String phone, String imageURL) {
        this.name = name;
        this.phone = phone;
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageURL() {
        return imageURL;
    }
}
