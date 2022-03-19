package com.platzi.market.web.controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired  //pq ProductService tiene la anotación de spring @Service
    private ProductService productService;

    public List<Product> getAll(){
        return productService.getAll();
    }

    public Optional<Product> getProduct(int id){
        return  productService.getProduct(id);
    }

    public Optional<List<Product>> getByCategory(int id){
        return productService.getByCategory(id);
    }

    public Product save(Product product){
        return productService.save(product);
    }

    public boolean delete(int id){
        return productService.delete(id);
    }

}
