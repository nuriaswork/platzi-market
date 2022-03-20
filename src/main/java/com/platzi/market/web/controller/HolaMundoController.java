package com.platzi.market.web.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/saludar")
public class HolaMundoController {

    @GetMapping("/hola")
    public String saludar(){
        return  "¡Nunca pares de aprender! 🚀";  //para mostrar el icono: Windows + .
    }

    @GetMapping("/{nombre}")
    public String saludar(@PathVariable String nombre){
        //nombre es parte de la url
        return  String.format("¡Nunca pares de aprender, %s ! ", nombre);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //con RequestParam, name va por parámetro en la url : url/?param=value
        return String.format("Hola %s! La hora es %h ", name, LocalTime.now());
    }

}
