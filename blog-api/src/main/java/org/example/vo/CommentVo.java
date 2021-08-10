package org.example.vo;

import lombok.Data;

import java.util.List;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/10
 */

@Data
public class CommentVo {

    private String id;

    /**
     * author： 评论人对信息
     */
    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private String createDate;

    /**
     * level: 评论层级， 1该层，2子层
     */
    private Integer level;

    /**
     * toUser: 对谁评论，  @功能
     */
    private UserVo toUser;



}
