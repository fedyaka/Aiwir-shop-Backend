package ru.fedyaka.SpringProject.service.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedyaka.SpringProject.entity.CategoryEntity;
import ru.fedyaka.SpringProject.repository.CategoryRepository;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public Iterable<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(long id){
        return categoryRepository.findById(id).get();
    }


    public CategoryEntity save(CategoryEntity entity){
        return categoryRepository.save(entity);
    }

    public void delete(long id){
        categoryRepository.delete(categoryRepository.findById(id).get());
    }

    public void update(long id, String name){
        CategoryEntity entity = categoryRepository.findById(id).get();
        entity.setName(name);
        categoryRepository.save(entity);
    }

}
