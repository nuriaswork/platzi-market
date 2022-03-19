package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   //podría poner @Component, pero es más detallado @Service
public class ProductService {

    @Autowired  //lo puedo usar pq ProductoRepository implements ProductRepository y este último está anotado
                // como @Repository, que es componente spring
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.getProduct(id);
    }

    public  Optional<List<Product>> getByCategory(int id){
        return productRepository.getByCategory(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int id) { //eliminarlo si existe y devuelve true; false e.o.c.
        return getProduct(id).map(p -> {
            productRepository.delete(id);
            return true;
        }).orElse( false);
    }

    public boolean delete2(int id) { //eliminarlo si existe y devuelve true; false e.o.c.
        if (getProduct(id).isPresent()) {
            productRepository.delete(id);
            return true;
        } else {
            return false;
        }
    }
}
