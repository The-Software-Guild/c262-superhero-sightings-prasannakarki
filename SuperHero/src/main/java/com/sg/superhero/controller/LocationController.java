package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDao;
import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.LocationDaoDBImpl;
import com.sg.superhero.dao.MemberDao;
import com.sg.superhero.entity.Hero;
import com.sg.superhero.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LocationController {



    @Autowired
    LocationDaoDBImpl locationDao;

    @GetMapping("locations")
    public String displayLocation(Model model){
        List<Location> locations = locationDao.getAllLocation();
        model.addAttribute("locations",locations);
        return "locations";

    }
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request){
        String locationName = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        Location location = new Location();

        location.setName(locationName);
        location.setDescription(description);
        location.setAddress(address);
        location.setLongitute(longitude);
        location.setLatitude(latitude);
        locationDao.addLocation(location);

        return "redirect:/locations";

    }


    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model){
        Location location = locationDao.getLocationById(id);

        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation( Location location) {
        locationDao.updateLocation(location);
        return "redirect:/locations";
    }



}
