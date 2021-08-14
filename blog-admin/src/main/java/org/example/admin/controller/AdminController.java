package org.example.admin.controller;

import org.example.admin.params.PageParam;
import org.example.admin.pojo.Permission;
import org.example.admin.service.PermissionService;
import org.example.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/13
 */

@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private PermissionService permissionService;


    @PostMapping("permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }



}
