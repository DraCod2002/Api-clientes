package com.pedro.Inventarios.controller;
import com.pedro.Inventarios.exception.RecurseNoEncontradoException;
import com.pedro.Inventarios.model.Proveedor;
import com.pedro.Inventarios.services.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {
    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/proveedores")
    public List<Proveedor> obtenerProveedores() {
        return proveedorServicio.listarProveedores();
    }

    @PostMapping("/proveedores")
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorServicio.guardarProveedor(proveedor);
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorId(@PathVariable int id) {
        Proveedor proveedor = proveedorServicio.buscarProveedorId(id);
        if (proveedor != null)
            return ResponseEntity.ok(proveedor);
        else
            throw new RecurseNoEncontradoException("No se encontró el id " + id);
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @PathVariable int id,
            @RequestBody Proveedor proveedorRecibido) {
        Proveedor proveedor = proveedorServicio.buscarProveedorId(id);
        proveedor.setNombre(proveedorRecibido.getNombre());
        proveedor.setDireccion(proveedorRecibido.getDireccion());
        proveedorServicio.guardarProveedor(proveedor);
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProveedor(@PathVariable int id) {
        Proveedor proveedor = proveedorServicio.buscarProveedorId(id);
        proveedorServicio.eliminarProveedor(proveedor.getIdProveedor());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
