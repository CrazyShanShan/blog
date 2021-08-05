package org.example.service;

import org.example.dao.pojo.SysUser;

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

}
