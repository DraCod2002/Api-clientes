package com.pedro.Inventarios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;
    String descripcion;
    Double precio;
    Integer exitencia;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    Proveedor proveedor;
}


