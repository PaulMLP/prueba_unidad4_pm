package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto producto) {
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscar(String codigoBarras) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigo", Producto.class);
		myQuery.setParameter("codigo", codigoBarras);
		try {
			return myQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ProductoSencillo buscarProducto(String codigoBarras) {
		Query myQuery = this.entityManager.createNativeQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.ProductoSencillo(p.codigoBarras, p.nombre, p.categoria, p.stock) FROM producto p WHERE prod_codigo_barras = :datoCodigo",
				ProductoSencillo.class);
		myQuery.setParameter("datoCodigo", codigoBarras);
		return (ProductoSencillo) myQuery.getSingleResult();
	}

	@Override
	public List<Producto> buscarTodos() {
		TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
		return myQuery.getResultList();
	}

}
