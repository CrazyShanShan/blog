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
}
