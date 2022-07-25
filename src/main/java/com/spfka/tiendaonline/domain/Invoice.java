package com.spfka.tiendaonline.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "factura")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {

    @Id
    @Column(name = "fac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    @Column(name = "ven_id_vendedor")
//    private Integer sellerId;
//
//    @Column(name = "cli_id_cliente")
//    private Integer customerId;

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

    public Invoice(Seller seller, Integer discount, Customer customer) {
        this.seller = seller;
        this.discount = discount;
        this.customer = customer;
    }

    public Invoice() {
    }
}
