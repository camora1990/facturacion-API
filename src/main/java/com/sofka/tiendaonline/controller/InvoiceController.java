package com.sofka.tiendaonline.controller;

import com.sofka.tiendaonline.domain.Invoice;
import com.sofka.tiendaonline.service.InvoiceService;
import com.sofka.tiendaonline.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    Response response;

    @GetMapping("/")
    public ResponseEntity<Response> getInvoices(){

        try {
            var invoices = invoiceService.getAll();
            response.responseMessage(true, HttpStatus.OK,"List invoices", invoices);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getInvoice(@PathVariable(name = "id") Integer id){

        try {
            var invoice = invoiceService.get(id);
            if (invoice.isEmpty()) {
                response.responseMessage(false,HttpStatus.NOT_FOUND, "Invoice not found in data base");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true, HttpStatus.OK,"Created", invoice);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteInvoice(@PathVariable(value = "id") Integer id){
        try{
            var invoice = invoiceService.delete(id);
            if (invoice.isEmpty()) {
                response.responseMessage(false,HttpStatus.NOT_FOUND, "Invoice not exist in database");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK, "Invoice removed successfully",invoice);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping( value = "/create")
    public ResponseEntity<Response> createInvoice(@RequestBody Invoice invoice){

        try {
            var newInvoice = invoiceService.create(invoice);
            response.responseMessage(true, HttpStatus.OK,"Created", newInvoice);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(true, HttpStatus.BAD_REQUEST,e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
