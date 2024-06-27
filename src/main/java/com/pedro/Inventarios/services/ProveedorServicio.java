package com.pedro.Inventarios.services;
import com.pedro.Inventarios.model.Proveedor;
import com.pedro.Inventarios.respository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProveedorServicio implements IProveedorServicio{
    @Autowired
    private ProveedorRepository proveedorRepositorio;

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    @Override
    public Proveedor buscarProveedorId(Integer idProveedor) {
        return proveedorRepositorio.findById(idProveedor).orElse(null);
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    @Override
    public void eliminarProveedor(Integer idProveedor) {
        proveedorRepositorio.deleteById(idProveedor);
    }
}
