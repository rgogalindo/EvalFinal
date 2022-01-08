package com.bootrest.main.servicios;

import java.util.List;
import java.util.Optional;

import com.bootrest.main.modelos.DetalleFactura;
import com.bootrest.main.modelos.Factura;

public interface TiendaServicio {

	//busca todas las facturas
	public List<Factura> findAllFacturas();
	
	//busca todas las facturas segun el RUC
	public List<Factura> findByRucFacturas(String ruc);
	
	//busca todas las facturas segun el ID
	public Optional<Factura> findByIdFactura(int id);
	
	//guarda una nueva factura
	public Factura saveFactura (Factura factura);
	
	//elimina una factura segun su ID
	public void deleteByIdFactura(int id);
	
	//obtiene un listado de todos los detalles
	public List<DetalleFactura> findAllDetalles();
	
	//obtiene un detalle segun su ID
	public Optional<DetalleFactura> findByIdDetalles(int id);
	
	//guarda un recurso de detalles
	public DetalleFactura saveDetalles(DetalleFactura detalles);
	
	//elimina un recurso de detalles segun su ID
	public void deleteByIdDetalles(int id);
	
	//obtiene un listado de detalles segun su codigo de factura
	public List<DetalleFactura> findByCodigoFactura(int codigoFactura);
	
	//elimina un listado de detalles segun su codigo de factura
	public List<DetalleFactura> deleteByAllCodigoFactura(int codFact);
}
