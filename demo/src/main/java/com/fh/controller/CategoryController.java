package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Category;
import com.fh.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("CategoryController")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryCategoryList")
    @ResponseBody
    public ServerResponse queryCategoryList(){
        List<Category> categoryList = categoryService.queryCategoryList();
        return ServerResponse.success(categoryList);
    }

    @RequestMapping("addCategory")
    @ResponseBody
    public ServerResponse addCategory(Category category){
        Integer id = categoryService.addCategory(category);
        return ServerResponse.success(id);
    }

    @RequestMapping("getCategoryById")
    @ResponseBody
    public ServerResponse getCategoryById(Integer id){
        Category category = categoryService.getCategoryById(id);
        return ServerResponse.success(category);
    }

    @RequestMapping("updateCategory")
    @ResponseBody
    public ServerResponse updateCategory(Category category){
        categoryService.updateCategory(category);
        return ServerResponse.success();
    }

    @RequestMapping("deleteCategory")
    @ResponseBody
    public ServerResponse deleteCategory(@RequestParam("ids[]")Integer[] ids){
        categoryService.deleteCategory(ids);
        return ServerResponse.success();
    }

}
