package com.sofka.tiendaonline.repository;

import com.sofka.tiendaonline.domain.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, Integer> {
}