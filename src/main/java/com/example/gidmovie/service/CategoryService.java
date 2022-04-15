package com.example.gidmovie.service;

import com.example.gidmovie.entity.Category;
import com.example.gidmovie.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesFromRequest(List<Integer> categoriesIds) {
        List<Category> categories = new ArrayList<>();
        for (Integer actor : categoriesIds) {
            categories.add(categoryRepository.getById(actor));
        }
        return categories;
    }
}
