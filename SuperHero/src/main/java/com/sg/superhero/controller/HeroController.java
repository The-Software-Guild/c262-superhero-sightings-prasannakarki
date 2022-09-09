package com.sg.superhero.controller;

import com.sg.superhero.dao.HeroDaoDBImpl;
import com.sg.superhero.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class HeroController {
    @Autowired
    HeroDaoDBImpl heroDao;

    @PostMapping("addHero")
    public String addHero(HttpServletRequest request){
        String heroName = request.getParameter("heroName");
        String heroDescription = request.getParameter("heroDescription");
        String super_power = request.getParameter("super_power");
        Hero hero = new Hero();

        hero.setName(heroName);
        hero.setPower(super_power);
        hero.setDescription(heroDescription);
        heroDao.addHero(hero);

        return "redirect:/heros";

    }

    @GetMapping("heros")
    public String displayHeros(Model model){
        List<Hero> heros = heroDao.getAllHeros();
        model.addAttribute("heros",heros);
        return "heros";
    }

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        heroDao.deleteHeroById(id);

        return "redirect:/heros";
    }

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.getHeroById(id);
        model.addAttribute("hero",hero);
        return "editHero";
    }
    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.getHeroById(id);
        hero.setName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("heroDescription"));
        hero.setPower(request.getParameter("super_power"));

        heroDao.updateHero(hero);

        return "redirect:/heros";
    }

}
