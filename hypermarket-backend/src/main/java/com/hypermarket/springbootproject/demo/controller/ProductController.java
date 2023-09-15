package com.hypermarket.springbootproject.demo.controller;

import com.hypermarket.springbootproject.demo.entity.Product;
import com.hypermarket.springbootproject.demo.service.ProductService;
import com.hypermarket.springbootproject.demo.dto.CombinedDto;
import com.hypermarket.springbootproject.demo.service.CombinedDtoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    public final ProductService productService;
    private final CombinedDtoServiceImpl productSaleEmployeeManufacturerService;

    @Autowired
    public ProductController(ProductService productService,
                             CombinedDtoServiceImpl productSaleEmployeeManufacturerService) {
        this.productService = productService;
        this.productSaleEmployeeManufacturerService = productSaleEmployeeManufacturerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/post")
    public ResponseEntity<Product> createProduct(@RequestParam String productName,
                                                 @RequestParam double price,
                                                 @RequestParam int manufacturerId,
                                                 @RequestParam int quantity){

        Product product = productService.createProduct(productName, price, manufacturerId, quantity);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/all-info-about-salesments")
    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo() {
        return productSaleEmployeeManufacturerService.getProductSaleEmployeeManufacturerInfo();
    }

    @GetMapping("/all-info-about-salesments/{productId}")
    public List<CombinedDto> getProductSaleEmployeeManufacturerInfo(@PathVariable int productId) {
        return productSaleEmployeeManufacturerService.getProductSaleEmployeeManufacturerInfo(productId);
    }

    @PostMapping("/sale")
    public ResponseEntity<Product> saleProduct(@RequestParam int productId,
                                               @RequestParam int quantity,
                                               @RequestParam int employeeId){

        productService.sellProduct(productId, quantity, employeeId);
        Product specificProduct = productService.getSpecificProduct(productId);


        return ResponseEntity.ok(specificProduct);
    }


}
