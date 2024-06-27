package com.pedro.Inventarios.respository;

import com.pedro.Inventarios.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository <Producto, Integer>{
}
