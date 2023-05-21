package org.example.mvc.controller;

import org.example.mvc.RequestMethod;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return "home";
    }
}