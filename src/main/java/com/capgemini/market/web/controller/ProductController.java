package com.capgemini.market.web.controller;

import com.capgemini.market.domain.Product;
import com.capgemini.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    public ResponseEntity<List<Product>> getAll(){//public List<Product> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK); //productService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product with and ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Product not found")
    })
    //Optional<Product> getProduct(@PathVariable("id") Integer productId){
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product",
            required = true, example = "7") @PathVariable("id") Integer productId){
        //return productService.getProduct(productId);
        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    //public Optional<List<Product>> getByCategory(@PathVariable("categoryId") Integer categoryId){
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") Integer categoryId){
        //return productService.getByCategory(categoryId);
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    //public Product save(@RequestBody Product product){
    public ResponseEntity<Product> save(@RequestBody Product product){
    // return productService.save(product);
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    //public boolean delete(@PathVariable("id")Integer productId){
    public ResponseEntity delete(@PathVariable("id")Integer productId){
        //return productService.delete(productId);
       if(productService.delete(productId)){
           return new ResponseEntity(HttpStatus.OK);

       }else{
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
    }

    @PostMapping("/update/{id}/{name}")
    public Optional<Product> update(@PathVariable("id") Integer productId, @PathVariable("name") String newName){
       return productService.updateName(productId,newName);
    }
}
