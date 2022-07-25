package com.sofka.tiendaonline.service;

import com.sofka.tiendaonline.domain.Invoice;
import com.sofka.tiendaonline.domain.dto.InvoiceDTO;
import com.sofka.tiendaonline.repository.DetailsRepository;
import com.sofka.tiendaonline.repository.InvoiceDTORepository;
import com.sofka.tiendaonline.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService{
    final
    InvoiceRepository invoiceRepository;
    final
    InvoiceDTORepository invoiceDTORepository;

    final DetailsRepository detailsRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceDTORepository invoiceDTORepository, DetailsRepository detailsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDTORepository = invoiceDTORepository;
        this.detailsRepository = detailsRepository;
    }

    public List<InvoiceDTO> getAll(){
        return invoiceDTORepository.findAll();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Invoice create(Invoice invoice ) throws Exception {
        var newInvoice = invoiceRepository.save(invoice);
        var details = invoice.getDetails().stream().map(ele -> {
            ele.setDiscount(10);
            ele.setQuantity(10);
            ele.setInvoiceId(newInvoice.getId());
            ele.setVat(10);
            return ele;
        }).toList();
        if (details.isEmpty()){
            throw new Exception("Details are required");
        }
        detailsRepository.saveAll(details);
        return newInvoice;
    }

    public Optional<InvoiceDTO> get(Integer id){
        return invoiceDTORepository.findById(id);
    }

    public Optional<InvoiceDTO> delete(Integer id){
        var getInvoice = invoiceDTORepository.findById(id);
        if (getInvoice.isEmpty()) {
            return Optional.empty();
        }
        invoiceDTORepository.deleteById(id);
        return getInvoice;

    }


}
