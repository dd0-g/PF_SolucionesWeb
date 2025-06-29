package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {
    @GetMapping("/acerca")
    public String mostrarAcerca(){
        return "acerca";
    }
    @GetMapping("/joyeria")
    public String mostrarJoyerias(){
        return "joyeria";
    }

    @GetMapping("/juegos")
    public String mostrarJuegos(){
        return "juegos";
    }

    @GetMapping("/prendas")
    public String mostrarPrendas(){
        return "prendas";
    }

}
