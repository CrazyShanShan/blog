package org.example.vo.params;

import lombok.Data;
import org.example.vo.CategoryVo;
import org.example.vo.TagVo;

import java.util.List;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/11
 */

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;

}
