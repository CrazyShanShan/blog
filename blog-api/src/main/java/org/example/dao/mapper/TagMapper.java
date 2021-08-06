package org.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

    /***
     * 根据文章ID查询标签列表
     * @Author: ZBZ
     * @Date: 2021/8/5
     * @param articleId: 文章id
     * @return: java.util.List<org.example.dao.pojo.Tag>
     **/
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签
     * 标签文章数量最多的limit个标签的id
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param limit: 最热标签的个数
     * @return: java.util.List<java.lang.Long>
     **/
    List<Long> findHotsTagIds(int limit);

    /**
     * 根据tag id 查询 tag
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param tagIds: tag ids
     * @return: java.util.List<org.example.dao.pojo.Tag>
     **/
    List<Tag> findTagsByTagId(List<Long> tagIds);
}
