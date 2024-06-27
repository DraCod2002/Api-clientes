package com.pedro.Inventarios.controller;

import com.pedro.Inventarios.exception.RecurseNoEncontradoException;
import com.pedro.Inventarios.model.Producto;
import com.pedro.Inventarios.model.Proveedor;
import com.pedro.Inventarios.services.ProductoServicio;
import com.pedro.Inventarios.services.ProveedorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto) {
        logger.info("Producto a agregar: " + producto);
        if (producto.getProveedor() != null) {
            Proveedor proveedor = proveedorServicio.buscarProveedorId(producto.getProveedor().getIdProveedor());
            if (proveedor != null) {
                producto.setProveedor(proveedor);
            } else {
                throw new RecurseNoEncontradoException("Proveedor no encontrado");
            }
        }
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoId(@PathVariable int id) {
        Producto producto = this.productoServicio.buscarProductoId(id);
        if (producto != null)
            return ResponseEntity.ok(producto);
        else
            throw new RecurseNoEncontradoException("No se encontró el id " + id);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido) {
        Producto producto = this.productoServicio.buscarProductoId(id);
        if (productoRecibido.getProveedor() != null) {
            Proveedor proveedor = proveedorServicio.buscarProveedorId(productoRecibido.getProveedor().getIdProveedor());
            if (proveedor != null) {
                producto.setProveedor(proveedor);
            } else {
                throw new RecurseNoEncontradoException("Proveedor no encontrado");
            }
        }
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExitencia(productoRecibido.getExitencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id) {
        Producto producto = productoServicio.buscarProductoId(id);
        this.productoServicio.eliminarProducto(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
