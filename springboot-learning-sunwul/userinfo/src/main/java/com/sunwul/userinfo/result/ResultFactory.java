package com.sunwul.userinfo.result;

/*****
 * @author: Sunwul
 * @date: 2019/12/25 18:40
 * PS: 响应信息工厂类
 */
public class ResultFactory {
    /**请求成功  200 */
    public  static Result Success(int code, String message, Object data){
        /**返回状态码,,校验信息,响应结果*/
        return buidResult(ResultCode.SUCCESS,message,data);
    }

    /**请求失败  400*/
    public static Result Fail(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }

    /**服务异常  500*/
    public static Result Server_Error(){
        return buidResult(ResultCode.INTERNAL_SERVER_ERROR,"",null);
    }

    /**请求的资源不存在  404*/
    public static Result Non_existent(){
        return buidResult(ResultCode.NOT_FOUND,"",null);
    }

    /**用户未认证  401*/
    public static Result Authentication(){
        return buidResult(ResultCode.UNAUTHORIZED,"",null);
    }

    /**重载buidResult 返回结果Result对象*/
    public static  Result buidResult(int resultCode,String message,Object data){
        return new Result(resultCode,message,data);
    }

    /**传递状态码,校验信息,响应结果*/
    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.code, message, data);
    }
}
