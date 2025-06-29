package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Administrador;
import com.example.demo.model.repository.IAdministradorRepository;

@Service
public class AdministradorService implements IAdministradorService{
    @Autowired
    private IAdministradorRepository administradorRepository;

    @Override
    public String guardarAdministrador(Administrador administrador) {
        String rpta = "";
        if(administrador.getId() == null){
            rpta = "Se registro el administrador correctamente";

        }else{
            rpta = "Se actualizo el administrador";
        }
        administradorRepository.save(administrador);
        return rpta;
    }

    @Override
    public List<Administrador> cargarAdministradores() {
        return (List<Administrador>)administradorRepository.findAll();
    }

    @Override
    public Administrador buscarAdministrador(Long id) {
        return administradorRepository.findById(id).get();
    }

    @Override
    public String eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
        return "Se elimino el administrador";
    }

}
