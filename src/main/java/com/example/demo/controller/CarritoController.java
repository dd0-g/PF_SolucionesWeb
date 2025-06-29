package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.entity.Producto;
import com.example.demo.model.service.IProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoController {
    @Autowired
    private IProductoService productoService;

    @SuppressWarnings("unchecked")
    @PostMapping("/agregar-al-carrito")
    public String agregarAlCarrito(
    @RequestParam String nombre,
    @RequestParam double precio,
    @RequestParam String imagen,
    @RequestParam(required = false, defaultValue = "") String redirect,
    HttpSession session
) {
    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
    if(carrito == null){
        carrito = new ArrayList<>();
    }
    boolean encontrado = false;
    for (Producto p : carrito) {
        if (p.getNombre().equals(nombre)) {
            p.setCantidad(p.getCantidad() + 1);
            encontrado = true;
            break;
        }
    }
    if (!encontrado) {
        Producto nuevo = new Producto(nombre, precio, imagen);
        nuevo.setCantidad(1);
        carrito.add(nuevo);
    }
    session.setAttribute("carrito", carrito);

    if ("joyeria".equals(redirect)) {
        return "redirect:/joyeria";
    } else if ("prendas".equals(redirect)) {
        return "redirect:/prendas";
    } else if ("juegos".equals(redirect)) {
        return "redirect:/juegos";
    }
    return "redirect:/";
}

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        int cantidad = (carrito != null) ? carrito.size() : 0;
        model.addAttribute("cantidadCarrito", cantidad);
        return "index";
    }

    @GetMapping("/carrito")
    public String verCarrito(@RequestParam(required = false, defaultValue = "") String from, HttpSession session, Model model){
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if(carrito == null){
        carrito = new ArrayList<>();
    }
    double total = 0;
    for (Producto p : carrito) {
        total += p.getPrecio() * p.getCantidad();
    }
        model.addAttribute("carrito", carrito);
        model.addAttribute("totalCarrito", total);
        model.addAttribute("from", from);
        return "carrito";
}

    @PostMapping("/carrito/sumar")
    public String sumarCantidad(@RequestParam int index, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito != null && index >= 0 && index < carrito.size()) {
            Producto producto = carrito.get(index);
            producto.setCantidad(producto.getCantidad() + 1);
        }
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/restar")
    public String restarCantidad(@RequestParam int index, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito != null && index >= 0 && index < carrito.size()) {
            Producto producto = carrito.get(index);
            if (producto.getCantidad() > 1) {
                producto.setCantidad(producto.getCantidad() - 1);
            }
        }
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar")
    public String eliminarProducto(@RequestParam int index, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito != null && index >= 0 && index < carrito.size()) {
            carrito.remove(index);
        }
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }
}

