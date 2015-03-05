package com.vtyurin.controller;

import com.vtyurin.domain.Category;
import com.vtyurin.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CategoryRestController {

    @Inject
    private CategoryService categoryService;

    @RequestMapping("/categories")
    public ModelAndView categories() {
        List<Category> categories = this.categoryService.findNestedCategoriesById(0L);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("message", "hello world!");
        return modelAndView;
    }
}
