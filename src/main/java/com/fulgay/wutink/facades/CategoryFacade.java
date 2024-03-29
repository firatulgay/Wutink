package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.converter.CategoryConverter;
import com.fulgay.wutink.converter.CategoryDropDownDtoConverter;
import com.fulgay.wutink.converter.CategoryDtoConverter;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDropdownDto;
import com.fulgay.wutink.dtos.CategoryDto;
import com.fulgay.wutink.service.CategoryService;
import com.fulgay.wutink.utils.validators.CategoryValidator;
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
    @Autowired
    private CategoryDropDownDtoConverter categoryDropDownDtoConverter;

    private GlobalMessages globalMessage;

    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDto> categoryDtoList = categoryDtoConverter.convertToList(categoryList);

        return categoryDtoList;
    }

    public List<CategoryDropdownDto> findAllCategoriesForDropdown() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDropdownDto> categoryDropdownDtoList = categoryDropDownDtoConverter.convertToList(categoryList);

        return categoryDropdownDtoList;
    }

    public CategoryDto findCategoryById(Long id) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryDtoConverter.convert(category);

        if (category == null) {
            GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,EnumErrorMessage.CATEGORY_NOT_FOUND.getValue());
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

                globalMessage = new GlobalMessages(EnumMessageType.CONF_MESSAGE,EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getValue());
                categoryDto.setGlobalMessage(globalMessage);
                LOG.info(categoryDto.getName() + " " + EnumSuccessMessage.CATEGORY_SAVE_SUCCESS.getValue());

            } else {
                GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,
                        categoryDto.getName() + " " + EnumErrorMessage.CATEGORY_ALREADY_EXIST.getValue());
                categoryDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE, EnumErrorMessage.CATEGORY_COULDNT_SAVE.getValue());
            categoryDto.setGlobalMessage(globalMessage);
        }
        return categoryDto;

    }

}
