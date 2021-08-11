package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.dao.mapper.CategoryMapper;
import org.example.dao.pojo.Category;
import org.example.service.CategoryService;
import org.example.vo.CategoryVo;
import org.example.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        return copy(category);
    }

    @Override
    public Result findAllDetail() {
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<>());

        return Result.success(copyList(categoryList));
    }

    @Override
    public Result categoryDetailById(Long id) {
        return Result.success(copy(categoryMapper.selectById(id)));
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);

        return Result.success(copyList(categoryList));
    }

    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }


}
