package com.hypermarket.springbootproject.demo.service;

import com.hypermarket.springbootproject.demo.entity.Manufacturer;
import com.hypermarket.springbootproject.demo.entity.Product;
import com.hypermarket.springbootproject.demo.entity.Sale;
import com.hypermarket.springbootproject.demo.exception.ProductNotFoundException;
import com.hypermarket.springbootproject.demo.exception.ProductOutOfStock;
import com.hypermarket.springbootproject.demo.repository.ManufacturerRepository;
import com.hypermarket.springbootproject.demo.repository.ProductRepository;
import com.hypermarket.springbootproject.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    private final SaleRepository saleRepository;
    private final SaleService saleService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              SaleRepository saleRepository, SaleService saleService) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.saleRepository = saleRepository;
        this.saleService = saleService;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSpecificProduct(int productId) {
        return productRepository.getReferenceById(productId);
    }

    @Override
    public Product createProduct(String productName, double price, int manufacturerId, int quantity) {
        Product product = new Product( 0,productName, price, new Date(), manufacturerId, quantity);

        productRepository.save(product);

        return product;
    }

    @Override
    public Product sellProduct(int productId, int quantity, int employeeId) {
        Product product = updateProductQuantity(productId, quantity);

        Sale sale = Sale.builder()
                .saleDate(new Date())
                .saleTime(new Time(System.currentTimeMillis()))
                .employeeId(employeeId)
                .productId(productId)
                .quantity(product.getQuantity())
                .build();

        saleService.saveSale(sale);

        return product;
    }

    @Override
    public Product updateProduct(Product newProduct, int productId) {
        Optional<Product> optionalOldProduct = productRepository.findById(productId);

        if (optionalOldProduct.isPresent()) {
            Product oldProduct = optionalOldProduct.get();

            oldProduct.setProductName(newProduct.getProductName());
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setQuantity(newProduct.getQuantity());
            oldProduct.setExpiryDate(newProduct.getExpiryDate());
            oldProduct.setManufacturerId(newProduct.getManufacturerId());


            productRepository.save(oldProduct);

            return oldProduct;
        } else
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
    }

    @Override
    public Manufacturer getProductManufacturer(int productId) {
        List<Manufacturer> allManufacturers = manufacturerRepository.findAll();

        List<Product> allProducts = getAllProducts();

        for (Product product : allProducts) {
            if(product.getProductId() == productId){
                for (Manufacturer manufacturer : allManufacturers) {
                    if(manufacturer.getManufacturerId() == product.getProductId()){
                        return manufacturer;
                    }
                }

            }
        }
        return null;
    }

    private Product updateProductQuantity(int productId, int newQuantity){
        Optional<Product> product = productRepository.findById(productId);

        int oldQuantity = product.get().getQuantity();

        if(oldQuantity < newQuantity){
            throw new ProductOutOfStock("Product wit ID "
                    + productId + " have only "
                    + product.get().getQuantity() + ", but expected: " + newQuantity);
        }

        oldQuantity -= newQuantity;

        product.get().setQuantity(oldQuantity);

        updateProduct(product.get(), productId);

        return product.get();
    }
}
