package com.sg.superhero.dao;

import com.sg.superhero.entity.Orginization;

import java.util.List;

public interface OrginizationDao {
    Orginization getOrginizationById(int id);

    List<Orginization> getAllOrginization();

    Orginization addOrginization(Orginization orginization);

    void updateOrginization(Orginization orginization);

    void deleteOrginizationById(int id);
}
