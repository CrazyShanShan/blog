package org.example.service;

import org.example.vo.Result;
import org.example.vo.params.LoginParam;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/6
 */
public interface LoginService {

    /**
     * 登陆功能
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param loginParam: {account: , password}
     * @return: org.example.vo.Result
     **/
    Result login(LoginParam loginParam);

}
