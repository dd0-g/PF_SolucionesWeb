package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.Administrador;

public interface IAdministradorService {
    public String guardarAdministrador(Administrador administrador);
    public List<Administrador> cargarAdministradores();
    public Administrador buscarAdministrador(Long id);
    public String eliminarAdministrador(Long id);
}
