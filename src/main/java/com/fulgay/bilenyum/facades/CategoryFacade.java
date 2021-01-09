package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.converter.Converter;
import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.populators.CategoryDtoPopulator;
import com.fulgay.bilenyum.populators.CategoryPopulator;
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

    private GlobalMessages globalMessage;
    private CategoryDto categoryDto;
    private CategoryDtoPopulator categoryDtoPopulator;
    private CategoryPopulator categoryPopulator;
    private Converter<Category, CategoryDto> dtoConverter;

    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryService.findAllCategories();

        categoryDtoPopulator = new CategoryDtoPopulator();
        dtoConverter = new Converter<>(categoryDtoPopulator);

        List<CategoryDto> categoryDtoList = dtoConverter.convertToTargetList(categoryList);

        return categoryDtoList;
    }

    public CategoryDto findCategoryById(Long id) {
        Category category = categoryService.findCategoryById(id);
        CategoryDto categoryDto = categoryDtoPopulator.populate(category);

        if (category == null) {
            GlobalMessages globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_NOT_FOUND.getDisplayValue());
            categoryDto.setGlobalMessage(globalMessage);
        }
        return categoryDto;
    }

    public CategoryDto save(CategoryDto categoryDto) {

        try {
            boolean isCategoryNameValid = categoryValidator.validateCategoryByCategoryName(categoryDto.getName());

            if (isCategoryNameValid) {
                categoryPopulator = new CategoryPopulator();

                Category category = categoryPopulator.populate(categoryDto);
                Long categoryId = categoryService.save(category);

                categoryDto.setId(categoryId);

                globalMessage = new GlobalMessages();
                globalMessage.setConfMessage(categoryDto.getName() + " " + EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getDisplayValue());
                categoryDto.setGlobalMessage(globalMessage);
                LOG.info(categoryDto.getName() + " " + EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getDisplayValue());

            } else {
                GlobalMessages globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(categoryDto.getName() + " " + EnumErrorMessage.CATEGORY_ALREADY_EXIST.getDisplayValue());
                categoryDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.CATEGORY_COULDNT_SAVE.getDisplayValue());
            categoryDto.setGlobalMessage(globalMessage);
        }
        return categoryDto;

    }

}
