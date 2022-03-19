package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//extends CrudRepository<Clase, TipoDeLaClavePrimaria>
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //otra forma de hacerlo sería con métodos nativos
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)

    //en camelcase: idCategoria > IdCategoria
    List<Producto> findByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //parámetros pueden ser de tipos primitivos. Hay que copiar en el nombre del query méthod el nombre exacto de
    //los atributos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);


}
