package com.sg.superhero.controller;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrginizationDaoDBImpl;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.entity.Orginization;
import com.sg.superhero.entity.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Controller
public class SightingController {
    @Autowired
    SightingDao sightingDao;
    @Autowired
    LocationDao locationDao;



    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request){
        String sightingHeroId = request.getParameter("hero");
        String location = request.getParameter("location");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
        LocalDateTime dateTime = date.atStartOfDay();

        Sighting sighting = new Sighting();

        sighting.setHeroId(Integer.parseInt(sightingHeroId));
        sighting.setLocation(Integer.parseInt(location));
        sighting.setDate(dateTime);
        sightingDao.addSighting(sighting);

        return "redirect:/sightings";

    }

    @GetMapping("sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.getAllSighting();
        model.addAttribute("sightings",sightings);
        return "sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/orginizations";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model){
        Sighting sighting = sightingDao.getSightingById(id);

        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("editSightings.html")
    public String performEditSighting( Sighting sighting) {
        sightingDao.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
