package com.sofka.tiendaonline.service;


import com.sofka.tiendaonline.domain.Seller;
import com.sofka.tiendaonline.repository.SellerRepository;
import com.sofka.tiendaonline.service.interfaces.ICrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService implements ICrudServices<Seller> {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller create(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> get(Integer id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Optional<Seller> delete(Integer id) {
        var seller = sellerRepository.findById(id);
        if (seller.isEmpty()) {
            return seller;
        }
        sellerRepository.deleteById(id);
        return seller;
    }

    @Override
    public Seller update(Seller seller, Integer id) {
        var getSeller = sellerRepository.findById(id).orElse(null);
        if (getSeller == null) {
            return null;
        }
        var name = seller.getName()== null? getSeller.getName():seller.getName();
        var email = seller.getEmail() == null ? getSeller.getEmail():seller.getEmail();
        return new Seller(id,email,name);
    }
}
