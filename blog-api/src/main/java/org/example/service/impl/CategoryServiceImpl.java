package org.example.service.impl;

import org.example.dao.mapper.CategoryMapper;
import org.example.dao.pojo.Category;
import org.example.service.CategoryService;
import org.example.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/7
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;


    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();

        BeanUtils.copyProperties(category, categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));

        return categoryVo;
    }
}
