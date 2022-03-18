package com.platzi.market.persistence.entity;

//EntityBeans = clases que mapean las tablas

import javax.persistence.*;

//por convención, el nombre de la clase será en singular
//con anotación Entity decimos a JPA que esta clase se mapea con una tabla
//con anotación Table decimos que la tabla física tiene otro nombre
@Entity
@Table(name="productos")
public class Producto {

    //no usar el tipo primitivo int, sino la clase Integer

    @Id                         //indicamos que es clave primaria sencilla
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //la clave es un serial (generado automáticamente por el gestorBD)
    @Column(name="id_producto") //por convención no se usan _ en los nombres y se usa camelCase, asi que relacionamos
                                // con el nombre físico de la columna
    private Integer idProducto;

    private String nombre;

    @Column(name="id_categoria")
    private Integer idCategoria;

    @Column(name="codigo_barras")
    private String codigoBarras;

    @Column(name="precio_venta")
    private Double precioVenta;

    @Column(name="cantidad_stock")
    private Integer cantidadStock;

    private  Boolean estado;

    //para generar los getter & setter Code>Generate (Alt+Insertar)

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}