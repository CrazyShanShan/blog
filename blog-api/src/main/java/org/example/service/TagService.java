package org.example.service;

import org.example.vo.Result;
import org.example.vo.TagVo;

import java.util.List;

/***
 * @Description: "tag service" 
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public interface TagService {
    /**
     * find tag by article id
     * @param articleId article id
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);


    /**
     * 最热标签
     * @param limit limit
     * @return
     */
    Result hots(int limit);

    /**
     * 所有标签
     * @Author: ZBZ
     * @Date: 2021/8/11

     * @return: org.example.vo.Result
     **/
    Result findAll();

    /**
     * 查询所有文章标签,细节
     * @Author: ZBZ
     * @Date: 2021/8/11

     * @return: org.example.vo.Result
     **/
    Result findAllDetail();

    /**
     * 根据tag id 查询tag detail
     * @Author: ZBZ
     * @Date: 2021/8/11
     * @param id: tag id
     * @return: org.example.vo.Result
     **/
    Result findDetailById(Long id);
}
