package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mchange.v1.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.dos.Archives;
import org.example.dao.mapper.ArticleBodyMapper;
import org.example.dao.mapper.ArticleMapper;
import org.example.dao.mapper.ArticleTagMapper;
import org.example.dao.pojo.Article;
import org.example.dao.pojo.ArticleBody;
import org.example.dao.pojo.ArticleTag;
import org.example.dao.pojo.SysUser;
import org.example.service.*;
import org.example.utils.UserThreadLocal;
import org.example.vo.ArticleBodyVo;
import org.example.vo.ArticleVo;
import org.example.vo.Result;
import org.example.vo.TagVo;
import org.example.vo.params.ArticleParam;
import org.example.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Description: "article service impl"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TagService tagService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ArticleBodyMapper articleBodyMapper;

    @Resource
    private CategoryService categoryService;




    @Resource
    private ArticleTagMapper articleTagMapper;

    @Resource
    private ThreadService threadService;


    @Override
    public Result listArticles(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> articlePage = articleMapper.listArticles(page, pageParams.getCategoryId(), pageParams.getTagId(), pageParams.getYear(), pageParams.getMonth());

        List<Article> records = articlePage.getRecords();
        return Result.success(copyList(records, true, true));
    }

    // @Override
    // public Result listArticles(PageParams pageParams) {
    //     /**
    //      * 分页查询 articlce 数据库表
    //      */
    //
    //     Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
    //
    //     LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    //
    //     if (pageParams.getCategoryId() != null) {
    //         queryWrapper.eq(Article::getCategoryId, pageParams.getCategoryId());
    //     }
    //
    //     List<Long> articleIdList = new ArrayList<>();
    //     if (pageParams.getTagId() != null) {
    //         LambdaQueryWrapper<ArticleTag> queryWrapper1 = new LambdaQueryWrapper<>();
    //         queryWrapper1.eq(ArticleTag::getTagId, pageParams.getTagId());
    //         List<ArticleTag> articleTags = articleTagMapper.selectList(queryWrapper1);
    //
    //         for (ArticleTag articleTag : articleTags) {
    //             articleIdList.add(articleTag.getArticleId());
    //         }
    //
    //         if (articleIdList.size() > 0) {
    //             queryWrapper.in(Article::getId, articleIdList);
    //         }
    //     }
    //
    //
    //     queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
    //     Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
    //     List<Article> records = articlePage.getRecords();
    //
    //     List<ArticleVo> articleVoList = copyList(records, true, true);
    //
    //     return Result.success(articleVoList);
    // }

    @Override
    public Result hotArticles(int limit) {
        /**
         * 查询 首页 最热文章
         */

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + limit);

        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticles(int limit) {
        /**
         * 查询 首页最新文章
         */

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getCreateDate);

        queryWrapper.select(Article::getId, Article::getTitle);

        queryWrapper.last("limit " + limit);

        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1。根据id查询 文章信息
         * 2。根据bodyId和Category 去做关联查询
         */
        // 获取文章信息
        Article article = articleMapper.selectById(articleId);
        // get article:id key
        String key = "article:" + articleId;
        String viewCount = stringRedisTemplate.opsForValue().get(key);
        // 更新的阅读量
        long n;
        if (viewCount == null) {
            // redis中没有阅读量, 那么则查询数据库中的文章信息
            // 然后给该key对应的value赋值
            stringRedisTemplate.opsForValue().set(key, String.valueOf(article.getViewCounts()));
        }
        // 直接从缓存中取数据并且加1
        n = stringRedisTemplate.opsForValue().increment(key);

        log.info("点击数： {}", n);

        ArticleVo articleVo = copy(article, true, true, true, true);
        articleVo.setViewCounts((int) n);
        return Result.success(articleVo);
    }

    @Override
    public void transViewCountFromRedisToDb() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 1. 获取所有的actile
        queryWrapper.select(Article::getId, Article::getViewCounts);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        String key;
        String viewCount;
        long articleId;
        for (Article article : articles) {
            articleId = article.getId();
            // 1. 获取文章点赞数
            key = "article:" + articleId;
            viewCount = stringRedisTemplate.opsForValue().get(key);
            log.info("更新的文章的ID:{}", articleId);
            log.info("当前文章的阅读数:{}", article.getViewCounts());

            if (viewCount != null) {
                // 2. 将文章点赞数存入表中
                Article updateArticle = new Article();
                updateArticle.setViewCounts(Integer.parseInt(viewCount));
                LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(Article::getId, articleId);
                updateWrapper.eq(Article::getViewCounts, article.getViewCounts());
                articleMapper.update(updateArticle, updateWrapper);
                log.info("更新之后的文章的阅读数为:{}", viewCount);
            }

        }
    }

    @Override
    public Result publish(ArticleParam articleParam) {
        // 此接口需要加入到登陆拦截器中
        // 获取登陆的用户
        SysUser sysUser = UserThreadLocal.get();
        /**
         * 1: 发布文章 目的： 构建Article对象
         * 2: Article对象需要有作者信息： 为当前的登陆用户
         * 3: 标签信息:  要将标签加入到关联列表中
         * 4: body内容存储: article bodyId
         */
        Article article = new Article();
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.Article_Common);
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
        // 插入后，形成article id
        articleMapper.insert(article);
        // bodyId
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);

        // tag
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }
        }

        // 更新body
        article.setBodyId(articleBody.getId());

        articleMapper.updateById(article);

        Map<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);

    }


    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : records) {
            articleVoList.add(copy(article, isTag, isAuthor, false, false));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
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

        if (isBody) {
            Long bodyId = article.getBodyId();
            res.setBody(findArticleBodyById(bodyId));
        }

        if (isCategory) {
            Long categoryId = article.getCategoryId();
            res.setCategory(categoryService.findCategoryById(categoryId));
        }

        return res;
    }

    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }


}
