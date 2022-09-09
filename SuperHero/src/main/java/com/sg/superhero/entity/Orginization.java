package com.sg.superhero.entity;

import java.util.Objects;

public class Orginization {
    private int id;
    private String name;
    private String description;
    private String address;
    private String contactInfo;
    private String allignment;

    public String getAllignment() {
        return allignment;
    }

    public void setAllignment(String allignment) {
        this.allignment = allignment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orginization)) return false;
        Orginization that = (Orginization) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getContactInfo(), that.getContactInfo()) && Objects.equals(getAllignment(), that.getAllignment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getAddress(), getContactInfo(), getAllignment());
    }
}
