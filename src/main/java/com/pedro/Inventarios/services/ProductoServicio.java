package com.pedro.Inventarios.services;

import com.pedro.Inventarios.model.Producto;
import com.pedro.Inventarios.respository.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServicio implements IProductoServicio{
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Override
    public List<Producto> listarProductos() {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoId(Integer idProducto) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.productoRepositorio.save(producto);

    }

    @Override
    public void eliminarProducto(Integer idProducto) {
        this.productoRepositorio.deleteById(idProducto);
    }
}
