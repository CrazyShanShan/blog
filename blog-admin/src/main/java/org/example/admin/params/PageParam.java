package org.example.admin.params;

import lombok.Data;

/***
 * @Description: "
 * @Author: ZBZ
 * @Date: 2021/8/13
 */
@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
