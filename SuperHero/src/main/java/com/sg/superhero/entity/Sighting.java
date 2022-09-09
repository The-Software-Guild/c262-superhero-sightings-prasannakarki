package com.sg.superhero.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sighting {
    private int id;
    private int heroId;
    private int location;
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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
        return getId() == sighting.getId() && getHeroId() == sighting.getHeroId() && getLocation() == sighting.getLocation() && Objects.equals(getDate(), sighting.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeroId(), getLocation(), getDate());
    }
}
