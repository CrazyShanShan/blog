package org.example.controller;

import org.example.common.aop.LogAnnotation;
import org.example.service.CommentsService;
import org.example.vo.Result;
import org.example.vo.params.CommentParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/10
 */

@RestController
@RequestMapping("comments")
public class CommentsController {

    @Resource
    private CommentsService commentsService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id) {
        return commentsService.commentsByArticleId(id);
    }



    @PostMapping("create/change")
    @LogAnnotation(module = "评论模块", operator = "添加新评论")
    public Result comment(@RequestBody CommentParam commentParam) {
        return commentsService.comment(commentParam);
    }




}
