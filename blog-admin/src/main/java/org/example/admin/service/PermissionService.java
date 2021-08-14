package org.example.admin.service;

import org.example.admin.params.PageParam;
import org.example.admin.pojo.Permission;
import org.example.admin.vo.Result;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/13
 */
public interface PermissionService {


    /**
     * list permission
     * @Author: ZBZ
     * @Date: 2021/8/13
     * @param pageParam:
     * @return: org.example.amin.vo.Result
     **/
    Result listPermission(PageParam pageParam);


    /**
     * add permission
     * @Author: ZBZ
     * @Date: 2021/8/13
     * @param permission:
     * @return: org.example.amin.vo.Result
     **/
    Result add(Permission permission);

    /**
     * update permission
     * @Author: ZBZ
     * @Date: 2021/8/13
     * @param permission:
     * @return: org.example.amin.vo.Result
     **/
    Result update(Permission permission);

    /**
     * delete permission
     * @Author: ZBZ
     * @Date: 2021/8/13
     * @param id:
     * @return: org.example.amin.vo.Result
     **/
    Result delete(Long id);
}
