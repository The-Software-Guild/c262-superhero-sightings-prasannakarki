package com.sg.superhero.entity;

import java.util.Objects;

public class Hero {
    private  int id;
    private String Name;
    private String description;
    private String power;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getId() == hero.getId() && Objects.equals(getName(), hero.getName()) && Objects.equals(getDescription(), hero.getDescription()) && Objects.equals(getPower(), hero.getPower());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPower());
    }
}
