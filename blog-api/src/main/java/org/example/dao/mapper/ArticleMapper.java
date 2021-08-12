package org.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.dao.dos.Archives;
import org.example.dao.pojo.Article;
import org.example.vo.params.PageParams;

import java.util.List;

/***
 * @Description: Article Mapper
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /***
     * 文章归档
     * @Author: ZBZ
     * @Date: 2021/8/6

     * @return: void
     **/
    List<Archives> listArchives();


    /**
     * 查询文章 根据 tagId categoryId (year and moth)
     * @Author: ZBZ
     * @Date: 2021/8/12
     * @param page: page
     * @param categoryId: category id
     * @param tagId: tag id
     * @param year: year
     * @param month: month
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<org.example.dao.pojo.Article>
     **/
    IPage<Article> listArticles(Page<Article> page, Long categoryId, Long tagId, String year, String month);
}
