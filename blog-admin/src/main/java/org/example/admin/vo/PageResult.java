package org.example.admin.vo;

import lombok.Data;

import java.util.List;

/***
 * @Description: "
 * @Author: ZBZ
 * @Date: 2021/8/13
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;

}
