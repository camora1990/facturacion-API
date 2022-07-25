package com.sofka.tiendaonline.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "detalle")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id", nullable = false)
    private Integer id;

    @Column(name = "det_cantidad", nullable = false)
    private Integer quantity;

    @Column(name = "det_iva", nullable = false)
    private Integer vat;

    @Column(name = "det_descuento", nullable = false)
    private Integer discount;

    @Column(name = "prod_id_producto")
    private Integer productId;

    @Column(name = "fac_id_factura")
    private Integer invoiceId;

//
//    @JoinColumn(name = "fac_id_factura", insertable = false, updatable = false)
//    @ManyToOne(targetEntity = Invoice.class, fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JsonBackReference(value = "invoice-details")
//    private Invoice invoiceId;

}
