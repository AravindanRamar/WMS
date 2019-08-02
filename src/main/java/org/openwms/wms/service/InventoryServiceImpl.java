package org.openwms.wms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.openwms.wms.bean.ProductBean;
import org.openwms.wms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Override
	public void addProduct(ProductBean productBean) {
		inventoryRepository.addProduct(productBean);
	}

	@Override
	public ProductBean updateProductInfo(ProductBean productBean) {
		
		return inventoryRepository.updateProductInfo(productBean);
	}

	@Override
	public List<ProductBean> getProducts() {
	
		return inventoryRepository.getProducts();
	}

	@Override
	public ProductBean findById(String productId) {
		
		return inventoryRepository.findById(productId);
	}

	@Override
	public void deleteProductById(String productId) {
		inventoryRepository.deleteProductById(productId);
	}

}
