package com.sg.superhero.controller;

import com.sg.superhero.dao.OrginizationDao;
import com.sg.superhero.dao.OrginizationDaoDBImpl;
import com.sg.superhero.entity.Hero;
import com.sg.superhero.entity.Location;
import com.sg.superhero.entity.Orginization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrginizationController {
    @Autowired
    OrginizationDaoDBImpl orginizationDao;



    @PostMapping("addOrginization")
    public String addORginization(HttpServletRequest request){
        String orginizationName = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String contactInfo = request.getParameter("contactInfo");
        String allignment = request.getParameter("allignment");

        Orginization org = new Orginization();

        org.setName(orginizationName);
        org.setAddress(address);
        org.setDescription(description);
        org.setContactInfo(contactInfo);
        org.setAllignment(allignment);
        orginizationDao.addOrginization(org);

        return "redirect:/orginizations";

    }

    @GetMapping("orginizations")
    public String displayOrginizations(Model model){
        List<Orginization> orginizations = orginizationDao.getAllOrginization();
        model.addAttribute("orginizations",orginizations);
        return "orginizations";
    }

    @GetMapping("deleteOrginization")
    public String deleteOrginization(Integer id) {
        orginizationDao.deleteOrginizationById(id);
        return "redirect:/orginizations";
    }

    @GetMapping("editOrginization")
    public String editOrginization(Integer id, Model model){
        Orginization orginization = orginizationDao.getOrginizationById(id);

        model.addAttribute("orginization", orginization);
        return "editOrginization";
    }

    @PostMapping("editOrginization")
    public String performEditOrginization( Orginization orginization) {
        orginizationDao.updateOrginization(orginization);
        return "redirect:/orginizations";
    }
    @GetMapping("orginizationDetail")
    public String orginizationDetailDetail(Integer id,Model model){
        Orginization orginization = orginizationDao.getOrginizationById(id);
        model.addAttribute("orginization",orginization);
        return "orginizationDetail";
    }
}
