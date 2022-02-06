package com.geekbrains.homework4.services;

import com.geekbrains.homework4.entities.Product;
import com.geekbrains.homework4.entities.ProductCategory;
import com.geekbrains.homework4.repository.ProductCategoriesRepository;
import com.geekbrains.homework4.repository.ProductsRepository;
import com.geekbrains.homework4.repository.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductCategoriesRepository productCategoriesRepository;

    public Page<Product> find(Integer minPrice, Integer maxPrice, String partTitle, Integer page, Long categorId){
        Specification<Product> specification = Specification.where(null);
        int pageSize = 3;
        if(minPrice != null){
            specification = specification.and(ProductsSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if(maxPrice != null){
            specification = specification.and(ProductsSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        if(partTitle != null){
            specification = specification.and(ProductsSpecifications.titleLike(partTitle));
        }
        specification = specification.and(ProductsSpecifications.categoryIdEqualTo(categorId));

        return productsRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
    }


    public List<Product> getProductsList() {
        return productsRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void removeItem(Long id) {
        productsRepository.deleteById(id);
    }

    public Product addItem(Product product){
        return productsRepository.save(product);
    }

    public List<ProductCategory> findAllCategories(){
        return productCategoriesRepository.findAll();
    }
}
