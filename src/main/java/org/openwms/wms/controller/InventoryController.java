package org.openwms.wms.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openwms.wms.bean.ProductBean;
import org.openwms.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/inventory" })
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@PostMapping(value = "/addProduct", headers = "Accept=application/json")
	public ResponseEntity<String> addProduct(@RequestBody ProductBean productBean) throws JSONException {
		JSONObject obj = new JSONObject();
		System.out.println("add product " + productBean.getProductName());
		ResponseEntity<String> response = null;
		inventoryService.addProduct(productBean);
		obj.put("success", true);
		obj.put("message", "Product added Successfully");
		response = new ResponseEntity<String>(obj.toString(), HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/updateProductInfo", headers = "Accept=application/json")
	public ResponseEntity<String> updateProductInfo(@RequestBody ProductBean productBean) throws JSONException {
		JSONObject obj = new JSONObject();
		System.out.println("updating product" + productBean.getProductId());
		ProductBean product = inventoryService.findById(productBean.getProductId());
		if (product == null) {
			obj.put("success", false);
			obj.put("message", "Product Info not updated, Product doesn't exist");
			return new ResponseEntity<String>(obj.toString(), HttpStatus.NOT_FOUND);
		}
		inventoryService.updateProductInfo(productBean);
		obj.put("success", true);
		obj.put("message", "Product Info updated");
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
	}

	@GetMapping(value = "/getInventory", headers = "Accept=application/json")
	public List<ProductBean> getAllProducts() {
		List<ProductBean> productList = inventoryService.getProducts();
		return productList;

	}

	@DeleteMapping(value = "/{productId}", headers = "Accept=application/json")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId) throws JSONException {
		JSONObject obj = new JSONObject();
		ProductBean product = inventoryService.findById(productId);
		if (product == null) {
			obj.put("success", false);
			obj.put("message", "Product does not exist");
			return new ResponseEntity<String>(obj.toString(), HttpStatus.NOT_FOUND);
		}
		inventoryService.deleteProductById(productId);
		obj.put("success", true);
		obj.put("message", "Product deleted");
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
	}

}
