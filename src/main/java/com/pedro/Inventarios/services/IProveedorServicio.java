package com.pedro.Inventarios.services;

import com.pedro.Inventarios.model.Proveedor;

import java.util.List;

public interface IProveedorServicio {
    List<Proveedor> listarProveedores();
    Proveedor buscarProveedorId(Integer idProveedor);
    Proveedor guardarProveedor(Proveedor proveedor);
    void eliminarProveedor(Integer idProveedor);
}
