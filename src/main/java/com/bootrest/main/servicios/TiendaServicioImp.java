package com.bootrest.main.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootrest.main.modelos.DetalleFactura;
import com.bootrest.main.modelos.Factura;
import com.bootrest.main.repositorios.DetallesRepositorio;
import com.bootrest.main.repositorios.FacturaRepositorio;

@Service
public class TiendaServicioImp implements TiendaServicio{

	@Autowired
	private FacturaRepositorio repo;
	@Autowired
	private DetallesRepositorio repoD;
	
	//busca todas las facturas
	@Override
	@Transactional(readOnly=true)
	public List<Factura> findAllFacturas() {
		return repo.findAll();
	}
	
	//busca todas las facturas segun el ID
	@Override
	@Transactional(readOnly=true)
	public Optional<Factura> findByIdFactura(int id) {
		return repo.findById(id);
	}

	//guarda una nueva factura
	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return repo.save(factura);
	}

	//elimina una factura segun su ID
	@Override
	@Transactional
	public void deleteByIdFactura(int id) {
		repo.deleteById(id);
	}

	//obtiene un listado de todos los detalles
	@Override
	@Transactional(readOnly=true)
	public List<DetalleFactura> findAllDetalles() {
		// TODO Auto-generated method stub
		return repoD.findAll();
	}

	//obtiene un detalle segun su ID
	@Override
	@Transactional(readOnly=true)
	public Optional<DetalleFactura> findByIdDetalles(int id) {
		// TODO Auto-generated method stub
		return repoD.findById(id);
	}
	
	//guarda un recurso de detalles
	@Override
	@Transactional
	public DetalleFactura saveDetalles(DetalleFactura detalles) {
		// TODO Auto-generated method stub
		return repoD.save(detalles);
	}

	//elimina un recurso de detalles segun su ID
	@Override
	@Transactional
	public void deleteByIdDetalles(int id) {
		// TODO Auto-generated method stub
		repoD.deleteById(id);
	}

	//busca todas las facturas segun el RUC
	@Override
	@Transactional(readOnly=true)
	public List<Factura> findByRucFacturas(String ruc) {
		return repo.findByRuc(ruc);
	}
	
	//elimina un listado de detalles segun su codigo de factura
	@Override
	@Transactional
	public List<DetalleFactura> deleteByAllCodigoFactura(int codFact) {
		// TODO Auto-generated method stub
		return repoD.deleteAllByCodigoFactura(codFact);
	}

	
	@Override
	@Transactional(readOnly=true)
	public List<DetalleFactura> findByCodigoFactura(int codigoFactura) {
		return repoD.findByCodigoFactura(codigoFactura);
	}

}
