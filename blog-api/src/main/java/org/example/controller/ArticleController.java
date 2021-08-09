package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.aop.LogAnnotation;
import org.example.service.ArticleService;
import org.example.vo.Result;
import org.example.vo.params.PageParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: "Article Controller"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@RestController
@RequestMapping("articles")
@Api("文章")
public class ArticleController {


    @Resource
    private ArticleService articleService;

    /**
     * 首页文章 列表
     * @Author: ZBZ
     * @Date: 2021/8/5
     * @param pageParams: page pageNum
     * @return: org.example.vo.Result
     **/
    @PostMapping
    @LogAnnotation(module = "文章", operator = "获取文章列表")
    @ApiOperation("根据传来的 page pageNUm 。返回首页文章 列表")
    public Result listArticles(@RequestBody PageParams pageParams) {
        return articleService.listArticles(pageParams);
    }


    /**
     * 首页 最热文章
     * @Author: ZBZ
     * @Date: 2021/8/6

     * @return: org.example.vo.Result
     **/
    @PostMapping("hot")
    @ApiOperation("首页 最热文章")
    @LogAnnotation(module = "文章", operator = "获取最热门的5篇文章")
    public Result hotArticles() {
        int limit = 5;
        return articleService.hotArticles(limit);
    }


    /**
     * 首页 最新文章
     * @Author: ZBZ
     * @Date: 2021/8/6

     * @return: org.example.vo.Result
     **/
    @PostMapping("new")
    @ApiOperation("首页 最新文章")
    @LogAnnotation(module = "文章", operator = "获取最新的5篇文章")
    public Result newArticles() {
        int limit = 5;
        return articleService.newArticles(limit);
    }




   /**
    * 首页文章 归档
    * @Author: ZBZ
    * @Date: 2021/8/6

    * @return: org.example.vo.Result
    **/
    @PostMapping("listArchives")
    @ApiOperation("首页 文章归档")
    @LogAnnotation(module = "文章", operator = "获取文章归档")
    public Result listArchives(){
        return articleService.listArchives();
    }


    /**
     * 首页点击文章， 查看文章信息
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param articleId: article id
     * @return: org.example.vo.Result
     **/
    @PostMapping("view/{id}")
    @ApiOperation("首页点击文章， 查看文章")
    public Result viewArticle(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }













}
