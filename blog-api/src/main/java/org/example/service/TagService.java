package org.example.service;

import org.example.vo.TagVo;

import java.util.List;

/***
 * @Description: "tag service" 
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public interface TagService {
    /**
     * 文章id
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

}
