package com.bootrest.main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootrest.main.modelos.DetalleFactura;
import com.bootrest.main.modelos.Factura;
import com.bootrest.main.servicios.TiendaServicio;

@RestController
@RequestMapping("/api")
public class ApiController {

	//maneja las inyecciones de dependencias
	@Autowired
	private TiendaServicio serv;
	
	//Guardar una nueva factura
	/* Ejemplo de un JSON para guardar una nueva factura
	 * {
    "nombreCliente":"John Doe",
    "ruc":"0123456723",
    "direccion":"Av. La Marina"
		}
	 */
	@PostMapping("/save/factura")
	public ResponseEntity<?> guardarFactura(@RequestBody Factura factura){
		try {
			Factura fact = serv.saveFactura(factura);
			return ResponseEntity.status(HttpStatus.OK).body(fact);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno.");
		}
	}
	
	// Ingresar un nuevo registro en el detallefactura
	/* Ejemplo de un JSON para guardar un nuevo registro
	 * {
        "producto": "Aceite",
        "cantidad": 3,
        "precio":15,
        "codigoFactura":1
		} 
	 */
	@PostMapping("/save/detalle")
	public ResponseEntity<?> guardarRegistroDetalles(@RequestBody DetalleFactura detalles){
		try {
			DetalleFactura det = serv.saveDetalles(detalles);
			return ResponseEntity.status(HttpStatus.OK).body(det);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno.");
		}
	}
	
	// Actualizar los campos nombreCliente, RUC y direccion de la tabla factura 
	// mediante el campo codigo
	
	/* Ejemplo de un JSON para actualizar una factura
	 * {
    "nombreCliente":"John Smith",
    "ruc":"2345670809",
    "direccion":"Av. Javier Prado"
		}
	 */
	@PutMapping("/update/factura/{id}")
	public ResponseEntity<?> actualizarCampos(@PathVariable int id, @RequestBody Factura factura){
		try {
			Factura fact = serv.findByIdFactura(id).get();
			fact.setNombreCliente(factura.getNombreCliente());
			fact.setRUC(fact.getRUC());
			fact.setDireccion(fact.getDireccion());
			return ResponseEntity.status(HttpStatus.OK).body(serv.saveFactura(fact));
	} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe la factura");
		}
	}
	
	// Eliminar uno o más registros de la tabla detallefactura mediante el campo codigoFactura
	@DeleteMapping("/delete/codFact/{codFact}")
	public ResponseEntity<?> borrarCodigoFactura(@PathVariable int codFact){
		try {
			List<DetalleFactura> dets = serv.deleteByAllCodigoFactura(codFact);
			return ResponseEntity.status(HttpStatus.OK).body(dets);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404, no existe el codigo de factura");
		}
	}
	
	//Obtener el listado de la tabla factura
	@GetMapping("/facturas")
	public ResponseEntity<?> obtenerFacturas(){
		try {
			List<Factura> facts = serv.findAllFacturas();
			return ResponseEntity.status(HttpStatus.OK).body(facts);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno.");
		}
	}
	//Obtener el listado de detalles
	@GetMapping("/detalles")
	public ResponseEntity<?> obtenerDetalles(){
		try {
			List<DetalleFactura> det = serv.findAllDetalles();
			return ResponseEntity.status(HttpStatus.OK).body(det);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno.");
		}
	}
	//Obtener dato de una factura, mediante el campo de RUC
	@GetMapping("/facturas/ruc/{ruc}")
	public ResponseEntity<?> obtenerDatoRuc(@PathVariable String ruc){
		try {
			List<Factura> facturas = serv.findByRucFacturas(ruc);
			return ResponseEntity.status(HttpStatus.OK).body(facturas);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, no existe el RUC");
		}
	}
	
	//Obtener el detalle de la factura mediante el RUC
	@GetMapping("/detalles/ruc/{ruc}")
	public ResponseEntity<?> obtenerDetallesRUC(@PathVariable String ruc){
		try {
			List<DetalleFactura> dets = new ArrayList<>();
			List<Factura> facturas = serv.findByRucFacturas(ruc);
			for (int i=0; i< facturas.size();i++) {
				List<DetalleFactura> dets1 = serv.findByCodigoFactura(facturas.get(i).getCodigo());
				dets.addAll(dets1);
			}
			return ResponseEntity.status(HttpStatus.OK).body(dets);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, no existe el RUC");
		}
	}
	
}
