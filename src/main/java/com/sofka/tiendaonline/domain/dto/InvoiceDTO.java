package com.sofka.tiendaonline.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sofka.tiendaonline.domain.Customer;
import com.sofka.tiendaonline.domain.Seller;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "factura")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InvoiceDTO {

    @Id
    @Column(name = "fac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Seller.class)
    @JoinColumn(name = "ven_id_vendedor")
    @JsonManagedReference(value = "seller-invoice")
    @JsonIgnore
    private Seller seller;

    @Column(name = "fac_descuento_general")
    private Integer discount;

    @JoinColumn(name = "cli_id_cliente", nullable = false)
    @ManyToOne(targetEntity = Customer.class)
    @JsonManagedReference(value = "customer-invoice")
    @JsonIgnore
    private Customer customer;

    @JsonManagedReference(value = "invoice-details")
    @OneToMany(mappedBy = "invoice")
    private List<DetailsDTO> details = new ArrayList<>();



}
