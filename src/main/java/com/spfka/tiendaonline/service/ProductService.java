package com.spfka.tiendaonline.service;

import com.spfka.tiendaonline.domain.Product;
import com.spfka.tiendaonline.domain.Seller;
import com.spfka.tiendaonline.repository.ProductRepository;
import com.spfka.tiendaonline.service.interfaces.ICrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ICrudServices<Product> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> delete(Integer id) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            return product;
        }
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public Product update(Product product, Integer id) {
        var getProduct = productRepository.findById(id).orElse(null);
        if (getProduct == null) {
            return null;
        }
        var name = product.getName()== null? getProduct.getName():product.getName();
        var price = product.getPrice() == null ? getProduct.getPrice():product.getPrice();
        var updateProduct = new Product(id,name,price);
        return productRepository.save(updateProduct);
    }
}
