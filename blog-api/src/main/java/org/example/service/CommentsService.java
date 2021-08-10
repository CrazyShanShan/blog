package org.example.service;

import org.example.vo.Result;
import org.example.vo.params.CommentParam;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/10
 */
public interface CommentsService {
    /**
     * 根据article id 获取该文章的评论信息列表
     * @Author: ZBZ
     * @Date: 2021/8/10
     * @param id: article id
     * @return: org.example.vo.Result
     **/
    Result commentsByArticleId(Long id);

    /**
     * comment
     * @Author: ZBZ
     * @Date: 2021/8/10
     * @param commentParam: coment param
     * @return: org.example.vo.Result
     **/

    Result comment(CommentParam commentParam);
}
