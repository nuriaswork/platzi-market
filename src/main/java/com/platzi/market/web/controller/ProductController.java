package com.platzi.market.web.controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired  //pq ProductService tiene la anotación de spring @Service
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>( productService.getAll(), HttpStatus.OK );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable  int id){ //si no se llama igual que el parámetro se pone
                                                            //@PathVariable("nombre_del_mapping")
        return  productService.getProduct(id).
                map(p-> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
                ;
    }

    @GetMapping("/category/{id}")  //si ponemos los dos GetMapping({id}) no sabría a cual llama pq los dos son int
    public ResponseEntity<List<Product>> getByCategory(@PathVariable  int id){
        return productService.getByCategory(id)
                .map(l->new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>( productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if ( productService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
