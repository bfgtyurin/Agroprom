package com.vtyurin.controller;

import com.vtyurin.domain.Product;
import com.vtyurin.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ProductController {

    @Inject
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView products() {
        List<Product> products = productService.findAllById();
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", products);
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute Product product) {
        productService.create(product);

        ModelAndView modelAndView = new ModelAndView("products");

        List<Product> products = productService.findAllById();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
