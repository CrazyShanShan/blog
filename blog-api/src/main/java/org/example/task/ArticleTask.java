package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ArticleService;
import org.example.service.CommentsService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/***
 * @Description: "同步缓存中的数据到数据库中"
 * @Author: ZBZ
 * @Date: 2021/8/10
 */
@Slf4j
public class ArticleTask extends QuartzJobBean {

    @Resource
    private ArticleService articleService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        articleService.transViewCountFromRedisToDb();
    }
}
