package com.github.gdiazs.sqlinjection.controllers;

import com.github.gdiazs.sqlinjection.models.UserSessionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String index(Model model, HttpServletRequest req){

        var userSession = (UserSessionModel) req.getSession(true).getAttribute("userSession");
        model.addAttribute("name", userSession.getUsername());

        return "dashboard/index";
    }
}
