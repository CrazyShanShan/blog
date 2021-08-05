package org.example.vo;

import lombok.Data;

import java.util.List;

/***
 * @Description: "article vo"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Data
public class ArticleVo {

    private String id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    // private ArticleBodyVo body;

    private List<TagVo> tags;

    // private CategoryVo category;
}
