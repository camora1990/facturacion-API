package com.spfka.tiendaonline.controller;

import com.spfka.tiendaonline.domain.Product;
import com.spfka.tiendaonline.service.ProductService;
import com.spfka.tiendaonline.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    Response response;


    @GetMapping(value = "/")
    public ResponseEntity<Response> getProducts() {
        try {
            var products = productService.getAll();
            response.responseMessage(true, HttpStatus.OK, "list products", products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createProduct(@RequestBody Product product) {
        try {
            var newProduct = productService.create(product);
            response.responseMessage(true, HttpStatus.CREATED, "product successfully created", newProduct);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getProduct(@PathVariable(name = "id") Integer id) {
        var selller = productService.get(id);
        if (selller.isEmpty()) {
            response.responseMessage(false,HttpStatus.NOT_FOUND, "Product not found in data base");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        response.responseMessage(true, HttpStatus.OK, "Product", selller);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable(value = "id") Integer id){
        try{
            var product = productService.delete(id);
            if (product.isEmpty()) {
                response.responseMessage(false,HttpStatus.NOT_FOUND, "Product not exist in database");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK, "Product removed successfully",product);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable(value = "id") Integer id, @RequestBody Product product){
        try {
            var productUpdate = productService.update(product, id);
            if (productUpdate == null) {
                response.responseMessage(false,HttpStatus.NOT_FOUND,"product not found");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK,"Successfully updated  Product",productUpdate);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
