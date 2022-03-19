package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

@Repository  //Indica a JAP que esta clase interactúa con la BD. Es un componente repository @Component es más general
public class ProductoRepository implements ProductRepository {
// implements ProductRepository para enfocar esta clase al dominio, en lugar de a una tabla
// así independiza dominio de gestor bd, por ejemplo, a una bd no relacional: solo hay que cambiar el mapper

    @Autowired   //IoC principio SOLID: Spring crea y administra el objeto EL OBJ DEBE SER UN COMPONENTE DE SPRING
    private ProductMapper productMapper; //Para poder ser un componente spring le pusimos en su clase el
                            //component-model="spring", pq tiene anotacion @Mapper que no es spring sino de mapstruct
                            // org.mapstruct


    @Autowired   //IoC principio SOLID: Spring crea y administra el objeto. EL OBJ DEBE SER UN COMPONENTE DE SPRING
    private ProductoCrudRepository productoCrudRepository;  //como extends CrudRepository, y este sí tiene anotación
                            //de que es un componente spring: @NoRepositoryBean estereotipo de repositorio de spring:
                            // org.springframework (es componentes de spring)

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos =  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
        //return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        /*
        // No, pq crud devuelve opcional: Optional.of(productMapper.toProducts(productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true)));
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        //no tenemos mapper de Optional, así que usamos el map, que ya devuelve un Optional de la expr lambda:
        return productos.map(prods -> productMapper.toProducts(prods));
         */
        return  getEscasos(quantity).map(p -> productMapper.toProducts(p));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(p -> productMapper.toProduct(p));
    }

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(productoCrudRepository.save(productMapper.toProducto(product)));
    }

/*
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoria(idCategoria);
    }

    public List<Producto> getByCategoriaOrdered(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
*/
    //antes public
    private Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
/*
    public  Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }
*/
    @Override
    public void delete(int id) {
        productoCrudRepository.deleteById(id);
    }


 }
