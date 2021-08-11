package org.example.service;

import org.example.dao.pojo.Category;
import org.example.vo.CategoryVo;
import org.example.vo.Result;

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

    /**
     * 返回所有目录
     * @Author: ZBZ
     * @Date: 2021/8/11

     * @return: org.example.vo.Result
     **/
    Result findAll();

    /**
     * 返回所有目录的细节
     * @Author: ZBZ
     * @Date: 2021/8/11

     * @return: org.example.vo.Result
     **/
    Result findAllDetail();
    
    /**
     * 根据ID返回目录的细节
     * @Author: ZBZ
     * @Date: 2021/8/11
     * @param id: catergory id
     * @return: org.example.vo.Result
     **/
    Result categoryDetailById(Long id);
}
