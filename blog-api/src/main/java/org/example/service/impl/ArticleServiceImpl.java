package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.dao.mapper.ArticleMapper;
import org.example.dao.pojo.Article;
import org.example.service.ArticleService;
import org.example.service.SysUserService;
import org.example.service.TagService;
import org.example.vo.ArticleVo;
import org.example.vo.Result;
import org.example.vo.TagVo;
import org.example.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * @Description: "article service impl"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagService tagService;

    @Resource
    private SysUserService sysUserService;


    @Override
    public Result listArticles(PageParams pageParams) {
        /**
         * 分页查询 articlce 数据库表
         */

        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();

        List<ArticleVo> articleVoList = copyList(records);

        return Result.success(articleVoList);
    }

    private List<ArticleVo> copyList(List<Article> records) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : records) {
            articleVoList.add(copy(article, true, true));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo res = new ArticleVo();
        BeanUtils.copyProperties(article, res);
        res.setId(String.valueOf(article.getId()));
        res.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));


        if (isTag) {
            Long articleId = article.getId();
            List<TagVo> tagVoList = tagService.findTagsByArticleId(articleId);
            res.setTags(tagVoList);
        }

        if (isAuthor) {
            Long authorId = article.getAuthorId();
            res.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }

        return res;
    }


}
