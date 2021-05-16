package com.http.convert;

import com.http.dto.CategoryDTO;
import com.http.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryConvert implements Convert<Category, CategoryDTO> {
    @Override
    public CategoryDTO toDTO(Category category) throws Exception {
        if (category == null) return null;
        return new CategoryDTO(category.getId(), category.getName(), category.getCreateDate());
    }

    @Override
    public List<CategoryDTO> toDtoList(List<Category> categories) throws Exception {
        if (categories == null) return null;
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            if (category != null)
                categoryDTOS.add(toDTO(category));
        }

        return categoryDTOS.isEmpty() ? null : categoryDTOS;
    }

    @Override
    public Category toModel(CategoryDTO categoryDTO) throws Exception {
        if (categoryDTO == null) return null;
        return new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getCreateDate());
    }

}
