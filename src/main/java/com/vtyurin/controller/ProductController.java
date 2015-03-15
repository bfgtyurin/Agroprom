package com.vtyurin.controller;

import com.vtyurin.domain.Product;
import com.vtyurin.service.CategoryService;
import com.vtyurin.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private static final String REQUEST_MAPPING_PRODUCT = "/product";
    private static final String REQUEST_MAPPING_PRODUCT_ADD = "/product/add";

    private static final String VIEW_PRODUCT_VIEW = "product/view";

    @Inject
    ProductService productService;

    @Inject
    CategoryService categoryService;

    @RequestMapping(value = REQUEST_MAPPING_PRODUCT, method = RequestMethod.GET)
    public ModelAndView products() {
        List<Product> products = productService.findAllById();
        Map<Long, String> categories = categoryService.findAllSimple();

        ModelAndView modelAndView = new ModelAndView(VIEW_PRODUCT_VIEW);
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }

    @RequestMapping(value = REQUEST_MAPPING_PRODUCT_ADD, method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:" + REQUEST_MAPPING_PRODUCT;
    }
}
