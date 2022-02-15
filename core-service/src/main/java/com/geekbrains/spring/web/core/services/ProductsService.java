package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.entities.ProductCategory;
import com.geekbrains.spring.web.core.repository.ProductCategoriesRepository;
import com.geekbrains.spring.web.core.repository.ProductsRepository;
import com.geekbrains.spring.web.core.repository.specifications.ProductsSpecifications;

import com.geekbrains.spring.web.core.soap.products.ProductSoap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductCategoriesRepository productCategoriesRepository;

    public static final Function<Product, ProductSoap> functionEntityToSoap = product -> {

        ProductSoap productSoap = new ProductSoap();
        productSoap.setId(product.getId());
        productSoap.setTitle(product.getTitle());
        productSoap.setPrice(product.getPrice());
        return productSoap;

    };

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

    public List<Product> findAll(){
        return productsRepository.findAll();
    }
}
