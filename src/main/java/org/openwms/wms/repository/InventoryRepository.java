package org.openwms.wms.repository;

import java.util.List;

import org.openwms.wms.bean.ProductBean;

public interface InventoryRepository {

	public void addProduct(ProductBean productBean);

	public ProductBean updateProductInfo(ProductBean productBean);

	public List<ProductBean> getProducts();

	public ProductBean findById(String productId);

	public void deleteProductById(String productId);

}
