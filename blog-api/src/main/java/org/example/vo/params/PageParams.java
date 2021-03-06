package org.example.vo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @Description: "page params vo"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@Data
public class PageParams {

    @ApiModelProperty(value = "第几页", allowableValues = "1")
    private int page = 1;

    @ApiModelProperty(value = "每页的数量", allowableValues = "10")
    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth() {
        if (this.month != null && this.month.length() == 1) {
            return "0" + this.month;
        }
        return this.month;
    }

}
