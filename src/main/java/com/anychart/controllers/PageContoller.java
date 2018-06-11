package com.anychart.controllers;

import com.anychart.dao.PersonDAO;
import com.anychart.models.Person;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;



@Controller
@RequestMapping("/")
public class PageContoller {

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String printWelcome(ModelMap model) {
        List<Person> persons = personDAO.getFruits(5);
        model.addAttribute("title", "Anychart Java template");
        model.addAttribute("chartTitle", "Top 5 persons");
        model.addAttribute("chartData", new Gson().toJson(persons));
        return "index";
    }
}
