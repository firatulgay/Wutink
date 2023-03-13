package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public interface CategoryDao extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
}
