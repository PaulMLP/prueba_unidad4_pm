package com.uce.edu.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.ProductoVenta;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.IGestorVentaService;

@Controller
@RequestMapping("/productos")
public class VentaController {

	@Autowired
	private IGestorVentaService gestorVentaService;

	private List<ProductoVenta> productosSeleccionados = new ArrayList<ProductoVenta>();

	@PostMapping("/vender")
	public String realizarVenta(Venta venta) throws SQLException {
		this.gestorVentaService.realizarVenta(productosSeleccionados, venta.getCedulaCliente(), venta.getNumero());
		return "redirect:/productos/vistaEleccion";
	}

	@GetMapping("/eleccion")
	public String buscarProducto(ProductoVenta productoVenta) {
		productosSeleccionados.add(productoVenta);
		return "vistaEleccion";
	}

	@GetMapping("/nuevaVenta")
	public String nuevoProducto(Venta venta) {
		return "vistaVenta";
	}
}
