package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.CategoryDropdownDto;
import com.fulgay.wutink.dtos.CategoryDto;
import com.fulgay.wutink.facades.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/categoryById/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return categoryFacade.findCategoryById(id);
    }

    @PostMapping("/saveCategory")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryFacade.save(categoryDto);

    }

    @GetMapping("/getCategories")
    public List<CategoryDto> findAllCategories(){
        return categoryFacade.findAllCategories();
    }

    @GetMapping("/getCategoriesForDropdown")
    public List<CategoryDropdownDto> findAllCategoriesForDropdown(){
        return categoryFacade.findAllCategoriesForDropdown();
    }

}
