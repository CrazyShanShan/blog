package org.example.service.impl;

import org.example.dao.mapper.TagMapper;
import org.example.dao.pojo.Tag;
import org.example.service.TagService;
import org.example.vo.Result;
import org.example.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;


    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        // mybatis plus 无法进行多表查询
        List<Tag> tagList = tagMapper.findTagsByArticleId(articleId);
        return copyList(tagList);
    }

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }


    @Override
    public Result hots(int limit) {

        List<Long> tagIds = tagMapper.findHotsTagIds(limit);

        if (CollectionUtils.isEmpty(tagIds)) {
            return Result.success(Collections.emptyList());
        }

        List<Tag> tagList = tagMapper.findTagsByTagId(tagIds);

        return Result.success(tagList);
    }
}
