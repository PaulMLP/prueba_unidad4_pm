package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	// GET
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Producto> lista = this.productoService.buscarTodos();
		modelo.addAttribute("productos", lista);
		return "vistaListaProductos";
	}

	@GetMapping("/buscarProducto/{codigoProducto}")
	public String buscarProducto(@PathVariable("codigoProducto") String codigo, Model modelo) {
		ProductoSencillo productoSencillo = this.productoService.b(codigo);
		modelo.addAttribute("productoSencillo", productoSencillo);
		return "vistaProducto";
	}
	
	@GetMapping("/buscarProducto/{codigoProducto}")
	public String buscarProducto(@PathVariable("codigoProducto") String codigo, Model modelo) {
		Producto producto = this.productoService.buscar(codigo);
		modelo.addAttribute("producto", producto);
		return "vistaProducto";
	}

	@PostMapping("/insertar")
	public String insertarProducto(Producto producto) {
		try {
			this.productoService.ingresarProducto(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/productos/buscar";
	}

	@GetMapping("/nuevoProducto")
	public String nuevoProducto(Producto producto) {
		return "vistaNuevoProducto";
	}

}
