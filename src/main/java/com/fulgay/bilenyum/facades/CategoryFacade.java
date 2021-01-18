package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.converter.CategoryConverter;
import com.fulgay.bilenyum.converter.CategoryDtoConverter;
import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.service.CategoryService;
import com.fulgay.bilenyum.utils.validators.CategoryValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryFacade {

    private static final Logger LOG = Logger.getLogger(CategoryFacade.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryValidator categoryValidator;
    @Autowired
    private CategoryDtoConverter categoryDtoConverter;
    @Autowired
    private CategoryConverter categoryConverter;

    private GlobalMessages globalMessage;

    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDto> categoryDtoList = categoryDtoConverter.convertToList(categoryList);

        return categoryDtoList;
    }

    public CategoryDto findCategoryById(Long id) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryDtoConverter.convert(category);

        if (category == null) {
            GlobalMessages globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_NOT_FOUND.getValue());
            categoryDto.setGlobalMessage(globalMessage);
        }
        return categoryDto;
    }

    public CategoryDto save(CategoryDto categoryDto) {

        try {
            boolean isCategoryExist = categoryValidator.validateIfCategoryExist(categoryDto.getName());

            if (!isCategoryExist) {

                Category category = categoryConverter.convert(categoryDto);
                Long categoryId = categoryService.save(category);

                categoryDto.setId(categoryId);

                globalMessage = new GlobalMessages();
                globalMessage.setConfMessage(categoryDto.getName() + " " + EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getValue());
                categoryDto.setGlobalMessage(globalMessage);
                LOG.info(categoryDto.getName() + " " + EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getValue());

            } else {
                GlobalMessages globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(categoryDto.getName() + " " + EnumErrorMessage.CATEGORY_ALREADY_EXIST.getValue());
                categoryDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_COULDNT_SAVE.getValue());
            categoryDto.setGlobalMessage(globalMessage);
        }
        return categoryDto;

    }

}
