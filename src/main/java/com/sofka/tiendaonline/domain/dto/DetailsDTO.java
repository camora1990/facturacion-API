package com.sofka.tiendaonline.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sofka.tiendaonline.domain.Product;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "detalle")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DetailsDTO {
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

    @JsonBackReference(value = "invoice-details")
    @JoinColumn(name = "fac_id_factura", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private InvoiceDTO invoice;



    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "product-details")
    @JoinColumn(name = "prod_id_producto", insertable = false, updatable = false)
    private Product product;

}
