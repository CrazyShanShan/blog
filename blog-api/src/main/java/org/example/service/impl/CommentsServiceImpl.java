package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.mapper.CommentMapper;
import org.example.dao.mapper.SysUserMapper;
import org.example.dao.pojo.Comment;
import org.example.dao.pojo.SysUser;
import org.example.service.CommentsService;
import org.example.service.SysUserService;
import org.example.utils.UserThreadLocal;
import org.example.vo.CommentVo;
import org.example.vo.Result;
import org.example.vo.UserVo;
import org.example.vo.params.CommentParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/10
 */
@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private SysUserService sysUserService;


    @Override
    public Result commentsByArticleId(Long id) {
        /**
         * 1. 根据文章id 以及 level = 1 查询评论列表 从comment表中查询, 时间近的放在前面
         * 2. 根据author id 查询该评论的作者信息
         * 3. 如果评论的level = 1， 则去查看它有没有子评论
         * 4. level = 2的为子评论， 则根据其parent id 进行查询
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.eq(Comment::getLevel, 1);
        queryWrapper.orderByDesc(Comment::getCreateDate);

        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = copyList(comments);

        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {

        SysUser sysUser = UserThreadLocal.get();

        Comment comment = new Comment();
        log.info("新增评论id: {}",comment.getId());
        comment.setArticleId(commentParam.getArticleId());



        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());

        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }

        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);

        commentMapper.insert(comment);

        return Result.success();
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();

        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }

        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        // id
        commentVo.setId(String.valueOf(comment.getId()));
        // 作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        // 子评论信息， 如果level = 1，
        Integer level = comment.getLevel();
        if (level == 1) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        } else if (level > 1) {
            // 这个子评论是给谁评论的
            Long toUid = comment.getToUid();
            UserVo toUserVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}

