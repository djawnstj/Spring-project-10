package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheck {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String home(HttpServletRequest req, HttpServletResponse res) {
        return "ok";
    }

}
