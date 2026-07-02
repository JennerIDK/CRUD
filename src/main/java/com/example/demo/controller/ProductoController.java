package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@Controller
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listar(Model model) {

        model.addAttribute("productos", service.listar());

        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("producto", new Producto());

        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {

        service.guardar(producto);

        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute("producto", service.buscar(id));

        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return "redirect:/";
    }

}