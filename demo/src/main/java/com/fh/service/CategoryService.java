package com.fh.service;

import com.fh.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategoryList();

    Integer addCategory(Category category);

    Category getCategoryById(Integer id);

    void deleteCategory(Integer[] ids);

    void updateCategory(Category category);

}
