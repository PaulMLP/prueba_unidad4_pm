package com.uce.edu.demo.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	private IProductoRepository productoRepository;

	// 1. Ingresar Productos
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingresarProducto(Producto producto) throws Exception {
		Producto p = this.productoRepository.buscar(producto.getCodigoBarras());
		if (p == null) {
			this.productoRepository.insertar(producto);
		} else {
			p.setStock(p.getStock() + producto.getStock());
			this.productoRepository.actualizar(p);
		}
	}

	// 4. Consultar Stock
	@Override
	@Transactional(value = TxType.REQUIRED)
	public Producto consultarStock(String codigoBarras) {
		return this.productoRepository.buscar(codigoBarras);
	}

	@Override
	public List<Producto> buscarTodos() {
		return this.productoRepository.buscarTodos();
	}

	@Override
	public Producto buscar(String codigoBarras) {
		return this.productoRepository.buscar(codigoBarras);
	}

	@Override
	public ProductoSencillo buscarSencillo(String codigoBarras) {
		// TODO Auto-generated method stub
		return this.productoRepository.buscarProducto(codigoBarras);
	}
}
