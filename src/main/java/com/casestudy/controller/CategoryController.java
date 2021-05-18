package com.casestudy.controller;

import com.casestudy.model.Category;
import com.casestudy.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"/view-create-cate"})
    public ModelAndView viewCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("/views/create-category");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message","");
        return modelAndView;
    }

    @PostMapping(value = {"/create-or-update-cate"})
    public ModelAndView createCategory(@ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("/views/create-category");
        String message = (category.getCateId() == null) ? "Successfully added new !" : "Update successful!";
        categoryService.save(category);
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message",message);
        return modelAndView;
    }

    @GetMapping(value = {"/view-edit-cate/{id}"})
    public ModelAndView viewEditCategory(@PathVariable String id) {
        ModelAndView modelAndView;Category category;
        try {
            category = categoryService.findById(Long.parseLong(id)).get();
            modelAndView = new ModelAndView("/views/create-category");
            modelAndView.addObject("category", category);
        }catch (Exception e){  modelAndView = new ModelAndView("/views/404");}
        return modelAndView;
    }
}
