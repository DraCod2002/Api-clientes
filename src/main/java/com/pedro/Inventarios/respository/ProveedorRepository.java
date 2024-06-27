package com.pedro.Inventarios.respository;

import com.pedro.Inventarios.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}
