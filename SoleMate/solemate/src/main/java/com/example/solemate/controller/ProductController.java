package com.example.solemate.controller;

import com.example.solemate.dao.ProductDAO;
import com.example.solemate.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping
    public List<Product> getAllProducts() throws Exception {

        return productDAO.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) throws Exception {
        return productDAO.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) throws Exception {
        productDAO.addProduct(product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productDetails) throws Exception {
        Product product = productDAO.getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setImageUrl(productDetails.getImageUrl());
        productDAO.updateProduct(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) throws Exception {
        productDAO.deleteProduct(id);
        return "Product deleted successfully!";
    }
}
