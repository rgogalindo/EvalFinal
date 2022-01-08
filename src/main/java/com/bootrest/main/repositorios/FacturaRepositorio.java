package com.bootrest.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootrest.main.modelos.Factura;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Integer>{

	public List<Factura> findByRuc(String ruc);
}
