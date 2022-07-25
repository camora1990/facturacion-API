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
@Table(name = "cliente")

public class Customer {

    @Id
    @Column(name = "cli_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cli_nombre", length = 100)
    private String name;

    @Column(name = "cli_telefono", nullable = true)
    private String celPhone;

    @Column(name = "cli_correo", unique = true)
    private String email;

    public Customer() {}
    public Customer(Integer id, String name, String celPhone, String email) {
        this.id = id;
        this.name = name;
        this.celPhone = celPhone;
        this.email = email;
    }

}
