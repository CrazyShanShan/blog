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
     * id
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);


    /**
     * 最热标签
     * @param limit
     * @return
     */
    Result hots(int limit);
}
