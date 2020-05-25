package com.sunwul.userinfo.result;

import java.util.Date;

/*****
 * @author: Sunwul
 * @date: 2019/12/25 17:39
 * PS: API响应信息封装
 * 控制Result权限,构建结果Result对象统一使用ResultFactory工厂类来创建
 */
public class Result {
    /**响应状态码*/
    private int code;

    /**响应提示信息*/
    private String message;

    /**响应结果*/
    private Object data;

    /**默认响应结果传递所有参数*/
    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
