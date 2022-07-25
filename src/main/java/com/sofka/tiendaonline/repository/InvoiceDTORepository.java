package com.sofka.tiendaonline.repository;

import com.sofka.tiendaonline.domain.dto.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDTORepository extends JpaRepository<InvoiceDTO, Integer> {
}