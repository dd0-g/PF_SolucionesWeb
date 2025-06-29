package com.example.demo.controller;

import com.example.demo.model.entity.DetallePedido;
import com.example.demo.model.service.IDetallePedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/detalle-pedidos")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detalleService;

    @GetMapping("/panel")
    public String panelDetalle(HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("rol"))) {
            return "redirect:/admin/login";
        }
        model.addAttribute("detalle", new DetallePedido());
        model.addAttribute("detalles", detalleService.cargarDetallePedidos());
        return "admin/detalle-pedidos/panel";
    }

    @PostMapping("/formulario")
    public String guardarDetalle(DetallePedido detalle, RedirectAttributes flash) {
        String respuesta = detalleService.guardarDetallePedido(detalle);
        flash.addFlashAttribute("respuestaDetalle", respuesta);
        return "redirect:/admin/detalle-pedidos/panel";
    }

    @GetMapping("/editar/{id}")
    public String editarDetalle(@PathVariable Long id, HttpSession session, Model model) {
        if (!"admin".equals(session.getAttribute("rol"))) {
            return "redirect:/admin/login";
        }
        model.addAttribute("detalle", detalleService.buscarDetallePedido(id));
        return "admin/detalle-pedidos/editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalle(@PathVariable Long id, RedirectAttributes flash) {
        String respuesta = detalleService.eliminarDetallePedido(id);
        flash.addFlashAttribute("respuestaDetalle", respuesta);
        return "redirect:/admin/detalle-pedidos/panel";
    }
}
