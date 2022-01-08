package com.bootrest.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootrest.main.modelos.DetalleFactura;

@Repository
public interface DetallesRepositorio extends JpaRepository<DetalleFactura, Integer> {

	public List<DetalleFactura> deleteAllByCodigoFactura(int codigoFactura);
	
	public List<DetalleFactura> findByCodigoFactura(int codigoFactura);
}
