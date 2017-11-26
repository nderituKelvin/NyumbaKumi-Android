package com.kenya.nyumbakumi;

/**
 * Created by Nderitu on 13/11/2017.
 */

public class ProvidersListy {
    private String name, phone;

    public ProvidersListy(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
