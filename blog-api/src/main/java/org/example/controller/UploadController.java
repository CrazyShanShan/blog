package org.example.controller;


import org.apache.commons.lang3.StringUtils;
import org.example.utils.QiniuUtils;
import org.example.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/11
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");

        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return Result.success(QiniuUtils.url + fileName);
        }
        return Result.fail(20001, "上传失败");

    }



}
