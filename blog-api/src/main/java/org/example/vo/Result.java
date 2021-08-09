package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Resource;

/***
 * @Description: "result"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */

@Data
@AllArgsConstructor
public class Result<T> {

    private boolean success;

    private int code;

    private String msg;

    private T data;

    public static<T> Result<T> success() {
        return new Result(true,200,"success", "");
    }


    public static <T> Result<T> success(T data) {
        return new Result(true, 200, "success", data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<T>(false, code, msg, null);
    }


}
