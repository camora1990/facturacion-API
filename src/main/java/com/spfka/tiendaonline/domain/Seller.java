package com.spfka.tiendaonline.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vendedor")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")
    private Integer id;

    @Column(name = "ven_correo", nullable = false, unique = true)
    private String email;

    @Column(name = "ven_nombre")
    private String name;


    public Seller(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Seller() {
    }
}
