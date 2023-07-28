package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findCategoryByName(String name);
}
