package org.example.utils;

import org.example.dao.pojo.SysUser;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/7
 */

public class UserThreadLocal {

    private UserThreadLocal(){};

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
