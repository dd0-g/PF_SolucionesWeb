package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Producto;
import com.example.demo.model.repository.IProductoRepository;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;


    @Override
    public String guardarProducto(Producto producto) {
        String rpta = "";
        if(producto.getId() == null){
            rpta = "Se registro el producto correctamente";

        }else{
            rpta = "Se actualizo el producto";
        }
        productoRepository.save(producto);
        return rpta;
    }

    @Override
    public List<Producto> cargarProductos() {
        return (List<Producto>)productoRepository.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public String eliminarProducto(Long id) {
        productoRepository.deleteById(id);
        return "Se elimino el producto";
    }

}
