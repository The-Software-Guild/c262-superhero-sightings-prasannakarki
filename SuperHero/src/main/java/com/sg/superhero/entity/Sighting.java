package com.sg.superhero.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
    private int id;
    private int hero;
    private int location;
    private LocalDateTime date;

    private Hero heros;

    public Hero getHeros() {
        return heros;
    }

    public void setHeros(Hero heros) {
        this.heros = heros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHero() {
        return hero;
    }

    public void setHero(int hero) {
        this.hero = hero;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() && getHero() == sighting.getHero() && getLocation() == sighting.getLocation() && Objects.equals(getDate(), sighting.getDate()) && Objects.equals(getHeros(), sighting.getHeros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHero(), getLocation(), getDate(), getHeros());
    }
}
