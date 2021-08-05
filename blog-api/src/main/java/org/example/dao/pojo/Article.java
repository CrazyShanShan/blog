package org.example.dao.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @Description: "文章"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Data
public class Article {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private Long id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章简介")
    private String summary;

    @ApiModelProperty("文章评论个数")
    private Integer commentCounts;

    @ApiModelProperty("文章阅读个数")
    private Integer viewCounts;

    @ApiModelProperty("作者id")
    private Long authorId;

    @ApiModelProperty("文章内容id")
    private Long bodyId;

    @ApiModelProperty("文章类别id")
    private Long categoryId;

    @ApiModelProperty("是否置顶")
    private Integer weight;

    @ApiModelProperty("创建时间")
    private Long createDate;
}
