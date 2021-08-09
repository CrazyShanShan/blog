package org.example.dao.pojo;

import lombok.Data;

/***
 * @Description: "
 * @Author: ZBZ
 * @Date: 2021/8/7
 */
@Data
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}
