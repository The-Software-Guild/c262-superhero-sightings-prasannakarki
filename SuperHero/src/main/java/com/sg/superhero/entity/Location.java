package com.sg.superhero.entity;

import java.util.Objects;

public class Location {
    private int id;
    private String name;
    private String address;
    private String description;
    private String longitute;
    private String latitude;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getId() == location.getId() && Objects.equals(getName(), location.getName()) && Objects.equals(getAddress(), location.getAddress()) && Objects.equals(getDescription(), location.getDescription()) && Objects.equals(getLongitute(), location.getLongitute()) && Objects.equals(getLatitude(), location.getLatitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getDescription(), getLongitute(), getLatitude());
    }
}
