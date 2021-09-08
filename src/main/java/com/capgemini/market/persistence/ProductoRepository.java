package com.capgemini.market.persistence;

import com.capgemini.market.domain.Product;
import com.capgemini.market.domain.repository.ProductRepository;
import com.capgemini.market.persistence.crud.ProductoCrudRepository;
import com.capgemini.market.persistence.entity.Producto;
import com.capgemini.market.persistence.mapper.ProductMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//o @Component(generalizacion[algo es componente de spring]) con esta anotacion, s spring que esta clase se encarga tambien de interactuar con la base de datos
@Repository //para indicar que es un componente que interactua con la BD, spring
//@Component //Generalizacion, se usa en clases genericas
public class ProductoRepository  implements ProductRepository {
    @Autowired //los obque reciben esta notacion le cedemos el control a spring para que haga las instancias
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMaper mapper;

    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(Integer categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(Integer quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(Integer productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProduto(product);//Se invoca el mapo inverso
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(Integer idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

    @Override
    public Optional<Product> updateName(Integer productId, String newName) {
        return Optional.empty();
    }

    //@Override
    //public Optional<Product> updateName(Integer productId, String newName) {
        //Optional<Product> pro = getProduct(productId);
        //Optional<Product> prod = mapper.toProduto(pro);
      //  return productoCrudRepository.updateNameById(productId,newName);
    //}

}
    /*public void save(Producto producto){
        productoCrudRepository.save(producto);
    }
    public void deleteById(Integer id){
        productoCrudRepository.deleteById(id);
    }
    public Long getCountRegister(){
        return productoCrudRepository.count();
    }*/

    //named Querys: son querys con nombramiento especial
/*
    public List<Producto> getByCategoria(Integer idCategoria){
        //llevar logica de negocio, miles de lineas
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    public Optional<List<Producto>> getEscasos(Integer cantidad){
     return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(Integer idProducto){
        return productoCrudRepository.findById(idProducto);
    }
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    }
    */


