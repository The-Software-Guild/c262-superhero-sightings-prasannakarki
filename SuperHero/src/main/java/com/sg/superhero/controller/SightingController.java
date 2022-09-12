package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrginizationDaoDBImpl;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.entity.Hero;
import com.sg.superhero.entity.Location;
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
    @Autowired
    HeroDao heroDao;



    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request){
        int sightingHeroId = Integer.parseInt(request.getParameter("hero"));
        int location = Integer.parseInt(request.getParameter("location"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
        LocalDateTime dateTime = date.atStartOfDay();

        Sighting sighting = new Sighting();

        sighting.setHero((sightingHeroId));
        sighting.setLocation((location));
        sighting.setDate(dateTime);
        sightingDao.addSighting(sighting);

        return "redirect:/sightings";

    }

    @GetMapping("sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.getAllSighting();
        List<Hero> heros = heroDao.getAllHeros();
        List<Location> locations = locationDao.getAllLocation();
        model.addAttribute("sightings",sightings);
        model.addAttribute("heros",heros);
        model.addAttribute("locations",locations);

        return "sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model){
        Sighting sighting = sightingDao.getSightingById(id);

        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting( Sighting sighting) {
        sightingDao.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
