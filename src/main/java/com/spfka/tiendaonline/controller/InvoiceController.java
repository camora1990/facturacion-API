package com.spfka.tiendaonline.controller;

import com.spfka.tiendaonline.domain.dto.InvoiceDTO;
import com.spfka.tiendaonline.service.InvoiceService;
import com.spfka.tiendaonline.utilities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping( value = "/create")
    public ResponseEntity<Response> createInvoice(@RequestBody InvoiceDTO invoice){



        try {
            var newInvoice = invoiceService.createDTO(invoice);
            response.responseMessage(true, HttpStatus.OK,"Created", newInvoice);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(true, HttpStatus.BAD_REQUEST,e.getCause().getCause().getMessage(), e.getCause());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

}
