package com.pedro.Inventarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProveedor;
    String nombre;
    String direccion;
}
