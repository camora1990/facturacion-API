package com.spfka.tiendaonline.repository;

import com.spfka.tiendaonline.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}