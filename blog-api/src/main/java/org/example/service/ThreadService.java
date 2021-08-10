package org.example.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.example.dao.mapper.ArticleMapper;
import org.example.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/9
 */

@Component
public class ThreadService {


    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {

        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);

        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        updateWrapper.eq(Article::getViewCounts, viewCounts);
        articleMapper.update(articleUpdate, updateWrapper);
    }

    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, long articleId, int n) {

        Article updateArticle = new Article();
        updateArticle.setViewCounts((int) n);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, articleId);
        articleMapper.update(updateArticle, updateWrapper);
    }

}
