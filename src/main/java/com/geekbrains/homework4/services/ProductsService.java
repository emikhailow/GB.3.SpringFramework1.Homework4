package com.geekbrains.homework4.services;

import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.repository.ProductsRepository;
import com.geekbrains.homework4.repository.specifications.ProductsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> find(Integer minPrice, Integer maxPrice, String partTitle, Integer page){
        Specification<Product> specification = Specification.where(null);
        int pageSize = 10;
        if(minPrice != null){
            specification = specification.and(ProductsSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if(maxPrice != null){
            specification = specification.and(ProductsSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        if(partTitle != null){
            specification = specification.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productsRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
    }


    public List<Product> getProductsList() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public void removeItem(Long id) {
        productsRepository.deleteById(id);
    }

    public Product addItem(Product product){
        return productsRepository.save(product);
    }

}
