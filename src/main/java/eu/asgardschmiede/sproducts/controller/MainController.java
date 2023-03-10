package eu.asgardschmiede.sproducts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Startseite");
        model.addAttribute("active", "home");
        return "standard";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        model.addAttribute("title", "Kontakt");
        model.addAttribute("active", "contact");
        return "standard";
    }
}