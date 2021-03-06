package org.example.service;

import org.example.vo.Result;
import org.example.vo.params.ArticleParam;
import org.example.vo.params.PageParams;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 查询最热文章
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param limit: limit
     * @return: org.example.vo.Result
     **/
    Result hotArticles(int limit);

    /**
     * 查询最新文章
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param limit: limit
     * @return: org.example.vo.Result
     **/
    Result newArticles(int limit);

    /**
     * 首页文章归档
     * @Author: ZBZ
     * @Date: 2021/8/6

     * @return: org.example.vo.Result
     **/
    Result listArchives();


    /**
     * find article with id
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param articleId: article id
     * @return: org.example.vo.Result
     **/
    Result findArticleById(Long articleId);


    /**
     *  将缓存从的数据访问量同步到数据库中
     * @Author: ZBZ
     * @Date: 2021/8/10
     * @return: void
     **/
    void transViewCountFromRedisToDb();



    /**
     * 发布文章
     * @Author: ZBZ
     * @Date: 2021/8/11
     * @param articleParam: article param
     * @return: void
     **/
    Result publish(ArticleParam articleParam);
}
