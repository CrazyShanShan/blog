package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.aop.LogAnnotation;
import org.example.service.TagService;
import org.example.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@RestController
@Api("标签")
@RequestMapping("tags")
public class TagController {

    @Resource
    private TagService tagService;

    @ApiOperation("返回最热标签的id 以及 标签名")
    @GetMapping("hot")
    @LogAnnotation(module = "标签模块", operator = "最热门标签")
    public Result hot() {
        int limit = 6;
        return tagService.hots(limit);
    }






}
