package com.capgemini.market.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //path principal

public class HolaMundoController {

    @GetMapping("/saludar")
    public String saludar(){
        return "Hola mundo en Spring!";
    }
}
