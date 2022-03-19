package com.platzi.market.web.controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired  //pq ProductService tiene la anotación de spring @Service
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable  int id){ //si no se llama igual que el parámetro se pone
                                                            //@PathVariable("nombre_del_mapping")
        return  productService.getProduct(id);
    }

    @GetMapping("/category/{id}")  //si ponemos los dos GetMapping({id}) no sabría a cual llama pq los dos son int
    public Optional<List<Product>> getByCategory(@PathVariable  int id){
        return productService.getByCategory(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id){
        return productService.delete(id);
    }

}
