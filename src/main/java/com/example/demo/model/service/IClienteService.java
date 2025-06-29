package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.Cliente;

public interface IClienteService {
    public String guardarCliente(Cliente cliente);
    public List<Cliente> cargarClientes();
    public Cliente buscarCliente(Long id);
    public String eliminarCliente(Long id);
}
