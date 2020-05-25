package com.sunwul.userinfo.controller;

import com.sunwul.userinfo.entity.UserInfo;
import com.sunwul.userinfo.result.Result;
import com.sunwul.userinfo.result.ResultCode;
import com.sunwul.userinfo.result.ResultFactory;
import com.sunwul.userinfo.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*****
 * @author: Sunwul
 * @date: 2019/12/30 18:37
 * PS: user控制器
 * @Api  Swagger-类注解 标识这个类是swagger的资源
 */
@Api(value = "UserInfoController",tags = {"用户信息控制"})
@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    int resultCode;

    /******以下为测试方法******/
    /**Swagger方法注解  @ApiOperation */
    @ApiOperation(value = "测试方法-以UserID获取信息", notes = "以ID查询单个用户信息")
    /**Swagger参数注解  @ApiImplicitParams*/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true,dataType = "string")
    })
    /**Swagger返回状态定义*/
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询到用户信息"),
            @ApiResponse(code = 400, message = "未查询到用户信息"),
            @ApiResponse(code = 404, message = "请求的服务不存在"),
            @ApiResponse(code = 500, message = "服务异常")}
    )
    @GetMapping("/user/{id}")
    public UserInfo login(@PathVariable("id") String id){
        return userInfoService.selectUserInfoById(id);
    }

    /**测试 Post请求*/
    @ApiOperation(value="测试方法-Post请求查询所有用户信息",notes="注意!-此处为危险接口")
    @RequestMapping(value = "/postcs", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<UserInfo> PostUser(){
        return userInfoService.findAllUserInfo();
    }
    /**测试 Get请求*/
    @ApiOperation(value = "测试方法-Get请求查询所有用户信息",notes = "注意!-此处为危险接口")
    @RequestMapping(value = "/getcs", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<UserInfo> GetUser(){
        return userInfoService.findAllUserInfo();
    }

    /******以上为测试方法 end********/


    /**
     * 前后端分离用的不同协议和端口,所以需要加入@CrossOrigin支持跨域.
     * 给UserInfo对象加入@Validated注解调用UserInfo实体类中定义的数据校验
     * @Validated({UserInfo.UserLoginCheckSequence.class})  调用指定的数据校验触发顺序,
     * 若使用@Valid 当验证同时触发时,会随机返回验证错误,所以此处使用@Validated替代
     * 验证信息是否符合要求,并在参数中加入BindingResult来获取错误信息.
     * 在逻辑处理中判断BindingResult是否含有错误信息,如果有错误信息,则直接返回错误信息.
     * @RequestBody 主要用来接收前端传递给后端的json字符串中的数据(请求体中的数据,get无请求体,所以前端必须用post方式提交数据)
     * 使用RequestMethod.POST来标识请求的方法类型
     * @Valid 激活校验
     */

    /**登录 /login */
    @ApiOperation(value = "登录接口",notes="处理前端登录请求,以用户名称和密码查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfo", value = "用户信息实体", required = true,dataType = "UserInfo"),
            @ApiImplicitParam(name = "brmessage", value = "接口错误信息", required = true,dataType = "BindingResult"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询到用户信息"),
            @ApiResponse(code = 400, message = "未查询到用户信息"),
            @ApiResponse(code = 404, message = "请求的服务不存在"),
            @ApiResponse(code = 500, message = "服务异常")}
    )
    @CrossOrigin
    /**前端提交请求的接口地址(除去自身的地址) , 指定response的content-type为application/json 返回json格式的数据 同时使用UTF-8防止中文乱码*/
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result login(@Validated({UserInfo.UserLoginCheckSequence.class}) @RequestBody UserInfo userinfo, BindingResult brmessage){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(brmessage.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,brmessage.getFieldError().getDefaultMessage());
            return ResultFactory.Fail(message);
        }
        /**验证用户名*/
        if(userInfoService.selectUserInfoByName(userinfo.getUsername())==null){
            String message = String.format("用户不存在!");
            return ResultFactory.Fail(message);
        }
        /**验证密码*/
        if(userInfoService.selectUseInfoByNameAndPwd(userinfo.getUsername(),userinfo.getUserpwd())==1){
            /**登录成功,返回数据
             * 只有两个参数参与了查询,此时userinfo只获取到了username和userpwd,所以此处只返回了username和userpwd
             * */
            return ResultFactory.Success(resultCode, "登录成功", userinfo);
        }
        /**默认密码错误*/
        String message = String.format("密码输入错误!");
        return ResultFactory.Fail(message);
    }


    /**注册 /sigin*/
    @ApiOperation(value = "注册接口",notes="处理前端注册请求,向数据库新增用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userinfo", value = "用户信息实体", required = true,dataType = "UserInfo"),
            @ApiImplicitParam(name = "brmessage", value = "接口错误信息", required = true,dataType = "BindingResult"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询到用户信息"),
            @ApiResponse(code = 400, message = "未查询到用户信息"),
            @ApiResponse(code = 404, message = "请求的服务不存在"),
            @ApiResponse(code = 500, message = "服务异常")}
    )
    @CrossOrigin
    @RequestMapping(value = "/sigin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result sigin(@Validated({UserInfo.UserSiginCheckSequence.class}) @RequestBody UserInfo userinfo, BindingResult brmessage){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(brmessage.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,brmessage.getFieldError().getDefaultMessage());
            /**返回错误信息*/
            return ResultFactory.Fail(message);
        }
        /**验证用户名*/
        if(userInfoService.selectUserInfoByName(userinfo.getUsername())!=null){
            String message = String.format("用户名已存在!");
            return ResultFactory.Fail(message);
        }
        /**注册,向数据库写入*/
        if(userInfoService.insertUserInfo(userinfo.getUserid(),userinfo.getUsername(),userinfo.getUserpwd(),userinfo.getUseremail())==1){
            /**注册成功,返回写入的数据*/
            return ResultFactory.Success(resultCode, "注册成功", userinfo);
        }
        return ResultFactory.buidResult(ResultCode.INTERNAL_SERVER_ERROR,"服务异常",null);
    }

    /**返回所有用户数据 /findAll */
    @ApiOperation(value = "返回所有用户信息",notes="向前端返回所有用户信息,此处为前端钩子函数在页面创建时触发,用以向页面渲染数据")
    @CrossOrigin
    @RequestMapping(value = "/findAll",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Result findAllUserInfo(@RequestBody UserInfo userInfo,BindingResult bindingResult){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(bindingResult.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,bindingResult.getFieldError().getDefaultMessage());
            /**返回错误信息*/
            return ResultFactory.Fail(message);
        }
        /**查询所有用户*/
        if(userInfoService.findAllUserInfo()!=null){
            /**定义数组接收查询到的值*/
            List<UserInfo> userlist = new ArrayList<UserInfo>();
            userlist=userInfoService.findAllUserInfo();
            return ResultFactory.Success(resultCode,"查询成功",userlist);
        }
        return ResultFactory.Fail("暂无数据");
    }

    /**删除用户数据 以userid --admin管理员*/
    @ApiOperation(value = "根据用户ID删除用户信息",notes="删除特定用户ID的所有信息")
    @CrossOrigin
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Result deleteUserById(@RequestBody UserInfo userInfo,BindingResult bindingResult){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(bindingResult.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,bindingResult.getFieldError().getDefaultMessage());
            /**返回错误信息*/
            return ResultFactory.Fail(message);
        }
        if(userInfoService.deleteUserInfoById(userInfo.getUserid())==1){
            /**删除成功*/
            return ResultFactory.Success(resultCode,"删除成功",null);
        }
        return ResultFactory.Fail("删除失败! 无权限");
    }

    /**未使用--删除用户数据 以用户名和密码(密码需二次确认,注销后因直接回退至登录页面) --用户本人注销*/
    @ApiOperation(value = "根据用户名与密码删除用户信息",notes="删除指定用户的所有信息")
    @CrossOrigin
    @RequestMapping(value = "/deleteUserByNameAndPwd",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Result deleteUserByNameAndPwd(@RequestBody UserInfo userInfo,BindingResult bindingResult){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(bindingResult.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,bindingResult.getFieldError().getDefaultMessage());
            /**返回错误信息*/
            return ResultFactory.Fail(message);
        }
        if(userInfoService.deleteUserInfoByNameAndPwd(userInfo.getUsername(),userInfo.getUserpwd())==1){
            /**删除成功,无权限*/
            return ResultFactory.Success(resultCode,"删除成功",null);
        }
        return ResultFactory.Fail("删除失败!无权限");
    }

    /**修改用户数据 以用户id*/
    @ApiOperation(value = "修改用户信息",notes="根据用户ID修改用户信息")
    @CrossOrigin
    @RequestMapping(value = "/updateUserInfoById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Result updateUserInfoById(@Validated({UserInfo.UserSiginCheckSequence.class}) @RequestBody UserInfo userInfo,BindingResult bindingResult){
        /**hasErrors()如果brmessage有错误,则进行下一步*/
        if(bindingResult.hasErrors()){
            /**String.format() 字符串常规类型格式化   %s-字符串的格式化*/
            String message=String.format("%s" ,bindingResult.getFieldError().getDefaultMessage());
            /**返回错误信息*/
            return ResultFactory.Fail(message);
        }

        /**验证用户名*/
        if(userInfoService.selectUserInfoByName(userInfo.getUsername())!=null){
            String message = String.format("用户名已存在!");
            return ResultFactory.Fail(message);
        }
        if(userInfoService.updateUserInfoById(userInfo.getUserid(),userInfo.getUsername(),userInfo.getUserpwd(),userInfo.getUseremail())==1){
            /**修改数据,返回修改后的数据*/
            return ResultFactory.Success(resultCode,"修改成功!",userInfo);
        }
        return ResultFactory.Fail("更新失败!无权限");
    }

}
