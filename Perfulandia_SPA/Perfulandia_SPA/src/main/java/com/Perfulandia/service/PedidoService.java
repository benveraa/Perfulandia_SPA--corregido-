package com.Perfulandia.service;

import com.Perfulandia.model.Cupon;
import com.Perfulandia.model.DetallePedido;
import com.Perfulandia.model.Pedido;
import com.Perfulandia.model.Cliente;
import com.Perfulandia.repository.CarritoRepository;
import com.Perfulandia.repository.CuponRepository;
import com.Perfulandia.repository.DetallePedidoRepository;
import com.Perfulandia.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Transactional
    public Pedido realizarPedido(Long clienteId, String codigoCupon) {
        List<Object[]> carrito = carritoRepository.obtenerCarritoPorCliente(clienteId);

        if (carrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Pedido pedido = new Pedido();
        pedido.setClienteId(clienteId);
        pedido.setFecha(new Date());
        pedido.setTotal(0.0);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        double total = 0.0;
        List<DetallePedido> detallesConNombre = new ArrayList<>();

        for (Object[] item : carrito) {
            Long productoId = (Long) item[0];
            Integer cantidad = (Integer) item[1];
            Double precioUnitario = (Double) item[2];
            String nombreProducto = (String) item[3];

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidoGuardado);
            detalle.setProductoId(productoId);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setNombreProducto(nombreProducto);

            total += precioUnitario * cantidad;
            detallePedidoRepository.save(detalle);

            detallesConNombre.add(detalle); // ✅ Guardamos para el PDF
        }

        double descuento = 0.0;
        if (codigoCupon != null && !codigoCupon.isEmpty()) {
            Cupon cupon = cuponRepository.findByCodigo(codigoCupon);
            if (cupon != null && cupon.isActivo()) {
                descuento = total * cupon.getDescuento();
                total -= descuento;
            }
        }

        pedidoGuardado.setTotal(total);
        pedidoGuardado.setDescuento(descuento);
        pedidoRepository.save(pedidoGuardado);
        carritoRepository.deleteByClienteId(clienteId);

        // ✅ Usamos los detalles que ya tienen el nombre del producto
        pedidoGuardado.setDetalles(detallesConNombre);

        // ✅ Generar PDF con los datos correctos
        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente ID " + clienteId);
        GeneradorBoletaPDF.generarBoleta(pedidoGuardado, cliente);

        return pedidoGuardado;
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
