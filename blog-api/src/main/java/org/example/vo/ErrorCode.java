package org.example.vo;

/***
 * @Description: error code
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
public enum ErrorCode {

    PARAMS_ERROR(10001, "参数错误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003, "TOKEN不合法")
    ;

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
