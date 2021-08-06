package org.example.vo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@Data
public class LoginParam {

    @ApiModelProperty(value = "账号", allowableValues = "root")
    private String account;

    @ApiModelProperty(value = "密码", allowableValues = "root")
    private String password;

    @ApiModelProperty(value = "用户昵称", allowableValues = "root")
    private String nickname;
}
