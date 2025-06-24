package com.Perfulandia.service;

import com.Perfulandia.model.DetallePedido;
import com.Perfulandia.model.Pedido;
import com.Perfulandia.model.Cliente;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class GeneradorBoletaPDF {

    public static void generarBoleta(Pedido pedido, Cliente cliente) {
        Document documento = new Document();

        try {
            String nombreArchivo = "boleta_pedido_" + pedido.getId() + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Fuente título
            Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("BOLETA DE COMPRA", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // Espacio

            // Fecha de compra
            String fechaFormateada = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(pedido.getFecha());
            documento.add(new Paragraph("Fecha: " + fechaFormateada));

            // Nombre del cliente
            documento.add(new Paragraph("Cliente: " + cliente.getNombre()));
            documento.add(new Paragraph(" "));

            // Tabla de productos
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.addCell("Producto");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio Unitario");
            tabla.addCell("Subtotal");

            List<DetallePedido> detalles = pedido.getDetalles();

            for (DetallePedido detalle : detalles) {
                // CAMBIO CLAVE: mostrar nombre del producto, no el ID
                tabla.addCell(detalle.getNombreProducto() != null ? detalle.getNombreProducto() : "Producto ID " + detalle.getProductoId());
                tabla.addCell(String.valueOf(detalle.getCantidad()));
                tabla.addCell("$" + detalle.getPrecioUnitario());
                tabla.addCell("$" + (detalle.getCantidad() * detalle.getPrecioUnitario()));
            }

            documento.add(tabla);
            documento.add(new Paragraph(" "));

            // Total y descuento (si aplica)
            documento.add(new Paragraph("TOTAL: $" + pedido.getTotal()));
            if (pedido.getDescuento() > 0) {
                documento.add(new Paragraph("Descuento aplicado: $" + pedido.getDescuento()));
            }

            documento.close();
            System.out.println("Boleta PDF generada: " + nombreArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
