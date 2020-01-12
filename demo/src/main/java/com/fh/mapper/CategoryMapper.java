package com.fh.mapper;

import com.fh.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    List<Category> queryCategoryList();

    void updateCategory(Category category);

    void deleteCategory(@Param("ids") Integer[] ids);

    Category getCategoryById(Integer id);

    void addCategory(Category category);

}
