package com.spfka.tiendaonline.controller;

import com.spfka.tiendaonline.domain.Seller;
import com.spfka.tiendaonline.service.SellerService;
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
@RequestMapping(value = "/api/v1/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @Autowired
    Response response;

    @GetMapping(value = "/")
    public ResponseEntity<Response> getSellers() {
        try {
            var sellers = sellerService.getAll();
            response.responseMessage(true, HttpStatus.OK, "list sellers", sellers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createSeller(@RequestBody Seller seller) {
        try {
            var newSeller = sellerService.create(seller);
            response.responseMessage(true, HttpStatus.CREATED, "Seller successfully created", newSeller);
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
    public ResponseEntity<Response> getSeller(@PathVariable(name = "id") Integer id) {
        var selller = sellerService.get(id);
        if (selller.isEmpty()) {
            response.responseMessage(false,HttpStatus.NOT_FOUND, "seller not found in data base");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        response.responseMessage(true, HttpStatus.OK, "seller", selller);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteseller(@PathVariable(value = "id") Integer id){
        try{
            var seller = sellerService.delete(id);
            if (seller.isEmpty()) {
                response.responseMessage(false,HttpStatus.NOT_FOUND, "seller not exist in database");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK, "seller removed successfully",seller);
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
    public ResponseEntity<Response> updateSeller(@PathVariable(value = "id") Integer id, @RequestBody Seller seller){
        try {
            var sellerUpdate = sellerService.update(seller, id);
            if (sellerUpdate == null) {
                response.responseMessage(false,HttpStatus.NOT_FOUND,"seller not found");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK,"Successfully updated  seller",sellerUpdate);
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
