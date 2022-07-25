package com.sofka.tiendaonline.controller;


import com.sofka.tiendaonline.domain.Customer;
import com.sofka.tiendaonline.service.CustomerService;
import com.sofka.tiendaonline.utilities.Response;
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


/**
 * Controlador para la libreta
 *
 * @author Camilo Morales Sanchez
 * @version 1.0.0
 */

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    Response response;

    /**
     * EndPoint get all customers
     *
     * @return All customers
     * @author Camilo Morales Sanchez
     */
    @GetMapping(value = "/")
    public ResponseEntity<Response> getCustomer() {
        try {
            var customers = customerService.getAll();
            response.responseMessage(true, HttpStatus.OK, "list customers", customers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Create customer Post
     * @param customer - Customer to create
     * @return - Response status customer
     */

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerService.create(customer);
            response.responseMessage(true, HttpStatus.CREATED, "Customer successfully created", newCustomer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.responseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get one customer getById
     * @param id - id customer to get
     * @return - response status get customer
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable(name = "id") Integer id) {
        var customer = customerService.get(id);
        if (customer.isEmpty()) {
            response.responseMessage(false,HttpStatus.NOT_FOUND, "Customer not found in data base");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        response.responseMessage(true, HttpStatus.OK, "Customer", customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param id - id user to delete
     * @return - response status delete customer
     */

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable(value = "id") Integer id){
        try{
            var customer = customerService.delete(id);
            if (customer.isEmpty()) {
                response.responseMessage(false,HttpStatus.NOT_FOUND, "Customer not exist in database");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK, "Customer removed successfully",customer);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (DataAccessException e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getCause().getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            response.responseMessage(false, HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id - id user to update
     * @param customer - attributes to update
     * @return - status request
     */

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer customer){
        try {
            var customerUpdate = customerService.update(customer, id);
            if (customerUpdate == null) {
                response.responseMessage(false,HttpStatus.NOT_FOUND,"Customer not found");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.responseMessage(true,HttpStatus.OK,"Successfully updated  customer",customerUpdate);
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
