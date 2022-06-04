package ru.fedyaka.SpringProject.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fedyaka.SpringProject.entity.ProductEntity;
import ru.fedyaka.SpringProject.repository.CategoryRepository;
import ru.fedyaka.SpringProject.repository.ProductRepository;


@Service
public class ProductService {

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    StorageService storageService;

    @Value("${LOCAL_PATH_IMAGE}")
    private String localPathImage;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, StorageService storageService){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.storageService = storageService;
    }


    public Iterable<ProductEntity> findAll(){
        Iterable<ProductEntity> productEntities = productRepository.findAll();
        return productEntities;
    }

    public ProductEntity findById(long id){
        return productRepository.findById(id).get();
    }


    public void create(MultipartFile file, String name, String description, Double cost, Long categoryId){
        ProductEntity entity = new ProductEntity();
        entity.setName(name);
        entity.setDescription(description);
        entity.setCost(cost);
        entity.setCategory(categoryRepository.findById(categoryId).get());
        entity.setImage(storageService.store(file, localPathImage));
        productRepository.save(entity);
    }
    public void update(long id, MultipartFile file, String name, String description, Double cost, Long categoryId){
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setName(name);
        productEntity.setDescription(description);
        productEntity.setCost(cost);
        productEntity.setCategory(categoryRepository.findById(categoryId).get());
        productEntity.setImage(storageService.update(productEntity.getImage(), file, localPathImage));
        productRepository.save(productEntity);
    }

    public void delete(long id){
        storageService.delete(productRepository.findById(id).get().getImage(), localPathImage);
        productRepository.deleteById(id);
    }

}
