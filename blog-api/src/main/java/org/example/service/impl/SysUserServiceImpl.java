package org.example.service.impl;

import org.example.dao.mapper.SysUserMapper;
import org.example.dao.pojo.SysUser;
import org.example.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setNickname("crazys");
        }
        return sysUser;
    }
}
