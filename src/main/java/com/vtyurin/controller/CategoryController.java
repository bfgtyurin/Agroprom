package com.vtyurin.controller;

import com.vtyurin.domain.Category;
import com.vtyurin.service.CategoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class CategoryController {

    private final static String REQUEST_MAPPING_CATEGORY = "/category";

    private final static String VIEW_CATEGORY = "category/view";

    @Inject
    CategoryService categoryService;

    @RequestMapping(value = REQUEST_MAPPING_CATEGORY, method = RequestMethod.GET)
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public Category byId(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}
