package com.fulgay.bilenyum.controllers;

import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.facades.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryFacade categoryFacade;

    @GetMapping("/categoryById/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return categoryFacade.findCategoryById(id);
    }

    @PostMapping("/saveCategory")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryFacade.save(categoryDto);

        //test
        //test
    }

    @GetMapping("/getCategories")
    public List<CategoryDto> findAllCategories(){
        return categoryFacade.findAllCategories();

        //test mesaj
        //test mesaj
        //test mesaj
        //test mesaj

    }

}
