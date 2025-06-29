package com.example.demo.controller;

import com.example.demo.model.entity.Producto;
import com.example.demo.model.service.IProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/panel")
    public String panelProductos(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("rol"))) {
            return "redirect:/admin/login";
        }
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoService.cargarProductos());
        return "admin/productos/panel";
    }

    @PostMapping("/formulario")
    public String guardarProducto(Producto producto, RedirectAttributes flash) {
        String respuesta = productoService.guardarProducto(producto);
        flash.addFlashAttribute("respuestaProducto", respuesta);
        return "redirect:/admin/productos/panel";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("rol"))) {
            return "redirect:/admin/login";
        }
        model.addAttribute("producto", productoService.buscarProducto(id));
        return "admin/productos/editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes flash) {
        String respuesta = productoService.eliminarProducto(id);
        flash.addFlashAttribute("respuestaProducto", respuesta);
        return "redirect:/admin/productos/panel";
    }
}
