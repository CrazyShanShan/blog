package org.example.vo;

import lombok.Data;

/***
 * @Description: "用户登陆的信息"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@Data
public class LoginUserVo {

    private String id;

    private String account;

    private String nickname;

    private String avatar;

}
