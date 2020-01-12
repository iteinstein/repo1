package com.fh.service.impl;

import com.fh.mapper.CategoryMapper;
import com.fh.model.Category;
import com.fh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryList() {
       List<Category> allCategoryList = categoryMapper.queryCategoryList();
       List<Category> categoryList = getChildren(allCategoryList,null);
       return categoryList;
    }

    @Override
    public Integer addCategory(Category category) {
        categoryMapper.addCategory(category);
        return category.getId();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public void deleteCategory(Integer[] ids) {
        categoryMapper.deleteCategory(ids);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    public List<Category> getChildren(List<Category> allCategoryList,Integer pid){
        List<Category> categoryList = new ArrayList<>();
        for(Category category : allCategoryList){
            if(category.getPid() == pid){
                List<Category> children = getChildren(allCategoryList,category.getId());
                category.setChildren(children);
                categoryList.add(category);
            }
        }
        return categoryList;
    }

}
