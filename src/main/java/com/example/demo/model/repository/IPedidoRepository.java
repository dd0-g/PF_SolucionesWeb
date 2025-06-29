package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Long>{

}
