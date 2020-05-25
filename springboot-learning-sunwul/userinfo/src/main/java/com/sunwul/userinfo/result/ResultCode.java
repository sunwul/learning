package com.sunwul.userinfo.result;

/*****
 * @author: Sunwul
 * @date: 2019/12/25 17:43
 * PS: 响应码枚举类,参考 HTTP 状态码的语义
 */
public enum  ResultCode {

    /**请求成功*/
    SUCCESS(200),
    /**请求失败*/
    FAIL(400),
    /**用户未认证(签名错误)*/
    UNAUTHORIZED(401),
    /**请求的资源不存在*/
    NOT_FOUND(404),
    /**服务器内部错误*/
    INTERNAL_SERVER_ERROR(500);

    public int code;

    /**传递状态码*/
    ResultCode(int code){
        this.code=code;
    }
}
