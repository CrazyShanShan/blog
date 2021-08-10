package org.example.vo.params;

import lombok.Data;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/10
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;

}
