package org.example.service;


import org.example.dao.pojo.SysUser;

/***
 * @Author: ZBZ
 * @Date: 2021/8/6
 **/
public interface TokenService {

    /***
     * 检查token是否合法
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param token: token
     * @return: org.example.dao.pojo.SysUser
     **/
    SysUser checkToken(String token);

}
