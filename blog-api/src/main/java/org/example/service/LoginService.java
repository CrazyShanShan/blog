package org.example.service;

import org.example.vo.Result;
import org.example.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 登出功能
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param token: token
     * @return: org.example.vo.Result
     **/
    Result logout(String token);

    /**
     * 注册
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param loginParam:
     * @return: org.example.vo.Result
     **/
    @Transactional(rollbackFor = Exception.class)
    Result register(LoginParam loginParam);
}
