package com.capgemini.market.persistence.crud;

import com.capgemini.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> { //La entity (tabla), el  tipo de llave primaria
/*
    DECODE: comparacion entre columnas y valoeres, no existe en sqlServer ni MySQL solo Oracle
    DECODE( [Si la columna] , [es 1] , [que me vas a regresar] , [si es 2] , [regresa un 4] )
    @Query(value = "SELECT DECODE(col1,1,2,4) from productos where id_categoria = ?", nativeQuery = true)

    @Query(value = "SELECT * from productos where id_categoria = ?", nativeQuery = true)
    List<Producto> findByCategory(Integer idCategoria);//Query Nativo
*/

    //NativeQuery
    @Query(value = "UPDATE u productos set nombre = u.name where id_producto = u.productId", nativeQuery = true)
    Optional<Producto> updateNameById(Integer productId, String name);

    //tipo de datos que va a regresar - nombrado especial
    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria); //QueryMethods

    //dato que puede ser llenado o no, evitar NoPointNull
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);



}