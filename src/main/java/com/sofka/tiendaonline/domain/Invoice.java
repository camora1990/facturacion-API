package com.sofka.tiendaonline.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "factura")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_id")
    private Integer id;

    @Column(name = "ven_id_vendedor")
    private Integer sellerId;
    @Column(name = "fac_descuento_general")
    private Integer discount;

    @Column(name = "cli_id_cliente")
    private Integer customerId;

    @Transient
    private List<Details> details = new ArrayList<>();



}
