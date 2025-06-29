package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Redirige siempre a la página de inicio, donde se despliega el modal de login.
     */
    @GetMapping("/login")
    public String mostrarLogin() {
        return "redirect:/";
    }

    /**
     * Procesa tanto login de administradores como de clientes.
     * Permite que el cliente ingrese con su email O con su nombre.
     */
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String usuario,
            @RequestParam String contrasena,
            HttpSession session,
            RedirectAttributes flash) {

        // 1) Intentar login de administrador
        String sqlAdmin = "SELECT * FROM administradores WHERE usuario = ? AND contrasena = ?";
        try {
            Map<String, Object> admin = jdbcTemplate.queryForMap(sqlAdmin, usuario, contrasena);
            session.setAttribute("usuario", admin.get("usuario"));
            session.setAttribute("rol", "admin");
            return "redirect:/admin/inicio";
        } catch (EmptyResultDataAccessException e) {
            // No es administrador; continuar a validación de cliente
        }

        // 2) Intentar login de cliente por email o nombre
        String sqlCliente =
            "SELECT * FROM clientes WHERE (email = ? OR nombre = ?) AND clave = ?";
        try {
            // Pasamos dos veces 'usuario' para email O nombre
            Map<String, Object> cliente = jdbcTemplate.queryForMap(
                    sqlCliente, usuario, usuario, contrasena);
            // Guardar datos en sesión
            session.setAttribute("usuario", cliente.get("nombre"));
            session.setAttribute("rol", "cliente");
            session.setAttribute("clienteId", cliente.get("ID_Cliente"));
            return "redirect:/";
        } catch (EmptyResultDataAccessException e) {
            // Falló login de cliente
        }

        // 3) Si llegamos aquí, credenciales incorrectas
        flash.addFlashAttribute("errorLogin", "Usuario o contraseña incorrectos");
        return "redirect:/";  
    }

    /**
     * Cierra la sesión y redirige al inicio.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
