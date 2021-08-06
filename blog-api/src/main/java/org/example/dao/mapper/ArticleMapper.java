package org.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.dao.dos.Archives;
import org.example.dao.pojo.Article;

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
}
