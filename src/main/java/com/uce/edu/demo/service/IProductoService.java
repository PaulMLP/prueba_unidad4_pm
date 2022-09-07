package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IProductoService {
	public void ingresarProducto(Producto producto) throws Exception;

	public Producto consultarStock(String codigoBarras);
	
	public List<Producto> buscarTodos();
	
	public Producto buscar(String codigoBarras);
	
	public ProductoSencillo buscarSencillo(String codigoBarras);
	
}
