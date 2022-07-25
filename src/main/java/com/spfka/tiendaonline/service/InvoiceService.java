package com.spfka.tiendaonline.service;

import com.spfka.tiendaonline.domain.dto.InvoiceDTO;
import com.spfka.tiendaonline.domain.Invoice;
import com.spfka.tiendaonline.repository.CustomerRepository;
import com.spfka.tiendaonline.repository.InvoiceRepository;
import com.spfka.tiendaonline.repository.SellerRepository;
import com.spfka.tiendaonline.service.interfaces.ICrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService implements ICrudServices<Invoice> {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public List<Invoice> getAll() {
        return null;
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> get(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Invoice> delete(Integer id) {
        return Optional.empty();
    }

    @Override
    public Invoice update(Invoice invoice, Integer id) {
        return null;
    }

    public Invoice createDTO(InvoiceDTO dto){
        var customer = customerRepository.findById(dto.getCustomer()).orElse(null);
        var seller = sellerRepository.findById(dto.getSeller()).orElse(null);
        var invoice = new Invoice(seller,dto.getDiscount(),customer);
        return this.create(invoice);
    }


}
