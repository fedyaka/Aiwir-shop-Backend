package ru.fedyaka.SpringProject.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedyaka.SpringProject.entity.CategoryEntity;
import ru.fedyaka.SpringProject.entity.ProductEntity;
import ru.fedyaka.SpringProject.repository.CategoryRepository;
import ru.fedyaka.SpringProject.repository.ProductRepository;

import java.util.HashSet;
import java.util.Set;


@Service
public class ProductService {

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public Iterable<ProductEntity> findAll(){
        Iterable<ProductEntity> productEntities = productRepository.findAll();
        return productEntities;
    }

    public ProductEntity findById(long id){
        return productRepository.findById(id).get();
    }


    public void create(String name, String description, Double cost, Long categoryId){
        ProductEntity entity = new ProductEntity();
        entity.setName(name);
        entity.setDescription(description);
        entity.setCost(cost);
        entity.setCategory(categoryRepository.findById(categoryId).get());
        productRepository.save(entity);
    }
    public void update(long id, String name, String description, Double cost, Long categoryId){
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setName(name);
        productEntity.setDescription(description);
        productEntity.setCost(cost);
        productEntity.setCategory(categoryRepository.findById(categoryId).get());
        productRepository.save(productEntity);
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }

}
