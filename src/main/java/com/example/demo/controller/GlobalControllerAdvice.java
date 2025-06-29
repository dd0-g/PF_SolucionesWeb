package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.entity.Producto;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("cantidadCarrito")
    public int cantidadCarrito(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        return (carrito != null) ? carrito.size() : 0;
    }

    @ModelAttribute("carrito")
    public List<Producto> carrito(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        return (carrito != null) ? carrito : new java.util.ArrayList<>();
    }

    @ModelAttribute("cantidadFavoritos")
    public int cantidadFavoritos(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> favoritos = (List<Producto>) session.getAttribute("favoritos");
        return (favoritos != null) ? favoritos.size() : 0;
    }

    @ModelAttribute("usuarioLogueado")
    public String usuarioLogueado(HttpSession session) {
        return (String) session.getAttribute("usuario");
    }
}