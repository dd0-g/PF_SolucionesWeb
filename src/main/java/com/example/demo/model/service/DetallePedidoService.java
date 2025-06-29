package com.example.demo.model.service;

import com.example.demo.model.entity.DetallePedido;
import com.example.demo.model.repository.IDetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService implements IDetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Override
    public String guardarDetallePedido(DetallePedido detalle) {
        String rpta = (detalle.getId() == null)
            ? "Se registró el detalle de pedido correctamente"
            : "Se actualizó el detalle de pedido";
        detallePedidoRepository.save(detalle);
        return rpta;
    }

    @Override
    public List<DetallePedido> cargarDetallePedidos() {
        return (List<DetallePedido>) detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido buscarDetallePedido(Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @Override
    public String eliminarDetallePedido(Long id) {
        detallePedidoRepository.deleteById(id);
        return "Se eliminó el detalle de pedido";
    }
}
