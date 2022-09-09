package com.sg.superhero.entity;

import java.util.Objects;

public class Member {
    private int heroId;
    private int orginizationId;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getOrginizationId() {
        return orginizationId;
    }

    public void setOrginizationId(int orginizationId) {
        this.orginizationId = orginizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return getHeroId() == member.getHeroId() && getOrginizationId() == member.getOrginizationId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeroId(), getOrginizationId());
    }
}
