package org.example.service;

import org.example.dao.pojo.Category;
import org.example.vo.CategoryVo;

/**
 * @Author: ZBZ
 * @Date: 2021/8/7
 **/
public interface CategoryService {
    /**
     * find category by id
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param categoryId: category id
     * @return: org.example.dao.pojo.Category
     **/
    CategoryVo findCategoryById(Long categoryId);
}
