package org.example.service;

import org.example.vo.Result;
import org.example.vo.params.PageParams;

import java.util.HashMap;
import java.util.Map;

/***
 * @Description: "article service"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public interface ArticleService {

    /***
     * 分页查询文章列表
     * @Author: ZBZ
     * @Date: 2021/8/5
     * @param pageParams: {page, pageNum}
     * @return: org.example.vo.Result<java.util.HashMap>
     **/
    Result listArticles(PageParams pageParams);
}
