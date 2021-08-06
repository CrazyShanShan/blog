package org.example.service;

import org.example.dao.pojo.SysUser;
import org.example.vo.Result;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public interface SysUserService {

    /**
     * find user by id
     * @param id
     * @return
     */
    SysUser findUserById(Long id);

    /***
     * 根据账号密码，取得SysUser实例
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param account: 账号
     * @param password: 密码
     * @return: org.example.dao.pojo.SysUser
     **/
    SysUser findUserByAccountPassword(String account, String password);

    /***
     * 根据TOKEN,取得SysUser实例
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param token: tokenn
     * @return: org.example.vo.Result
     **/
    Result findUserByToken(String token);
}
