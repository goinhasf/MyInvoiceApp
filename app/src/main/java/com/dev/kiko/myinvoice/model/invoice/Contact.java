package com.dev.kiko.myinvoice.model.invoice;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

public class Contact {

    private String name;
    private String address;
    private String email;
    private String telNum;
    private String website;
    private String faxNum;

    private Contact() {
        name = "";
        address = "";
        email = "";
        telNum = "";
        website = "";
        faxNum = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    class Builder {

        public Builder() {

        }

        public Contact newContact() {
            return new Contact();
        }
    }
}
