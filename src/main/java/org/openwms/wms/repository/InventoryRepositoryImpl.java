package org.openwms.wms.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openwms.wms.bean.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

	private final static String UNCHECKED = "unchecked";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addProduct(ProductBean productBean) {
		Session session = sessionFactory.getCurrentSession();
		session.save(productBean);
	}

	@Override
	public ProductBean updateProductInfo(ProductBean productBean) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(findByProduct(productBean.getProductId()));
		ProductBean product = findByProduct(productBean.getProductId());
		product.setProductName(productBean.getProductName());
		product.setQuantity(productBean.getQuantity());
		product.setUpdated_date(productBean.getUpdated_date());
		session.update(product);
		return product;
	}
	
	public ProductBean findByProduct(String productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ProductBean where productId=:productId");
		query.setParameter("productId", productId);
		return (ProductBean) query.uniqueResult();
	}

	@Override
	public List<ProductBean> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings(UNCHECKED)
		List<ProductBean> list = session.createCriteria(ProductBean.class).list();
		return list;
	}

	@Override
	public ProductBean findById(String productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ProductBean where productId=:productId");
		query.setParameter("productId", productId);
		return (ProductBean) query.uniqueResult();
	}

	@Override
	public void deleteProductById(String productId) {
		Session session = sessionFactory.getCurrentSession();
		ProductBean product = findById(productId);
		session.delete(product);
	}


}
