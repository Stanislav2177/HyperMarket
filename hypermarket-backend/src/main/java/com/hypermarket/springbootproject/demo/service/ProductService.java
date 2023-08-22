package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import com.hypermarket.springbootproject.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSpecificProduct(int productId);
    Product createProduct(String productName, double price, int manufacturerId, int quantity);


    Product sellProduct(int productId, int quantity, int employeeId);
    Product updateProduct(Product product, int productId);

    Manufacturer getProductManufacturer(int productId);



}
