package com.sunwul.userinfo.mapper;

import com.sunwul.userinfo.entity.UserInfo;

import java.util.List;

/*****
 * @author: Sunwul
 * @date: 2020/1/5 14:45
 * PS: 不用写 @Mapper 注解,因为启动类已经开启了自动扫描 @MapperScan
 * 数据访问
 */
public interface UserInfoMapper {

    /**根据id查  测试用*/
    UserInfo SelUserInfoByUserId(String id);

    /**当前用户所有信息*/
    UserInfo SelUserInfo(String username, String userpwd);

    /**用户名是否存在*/
    String SelUserInfoByName(String username);

    /**用户是否存在*/
    int SelUseInfoByNameAndPwd(String username, String userpwd);

    /**查询所有用户(返回一个UserInfo的集合)  admin*/
    List<UserInfo> FindAllUserInfo();

    /**新增用户*/
    int InsUserInfo(String userid, String username, String userpwd, String useremail);

    /**删除用户数据 以userid --admin管理员*/
    int DelUserInfoById(String userid);

    /**删除用户数据 以用户名和密码(密码需二次确认,注销后直接回退至登录页面) --用户本人注销*/
    /**密码需二次确认的话,可以直接验证密码的正确性,返回是否存在*/
    int DelUserInfoByNameAndPwd(String username,String userpwd);

    /**修改用户数据 以用户id*/
    /**修改完需验证用户名是否存在 __SelUserInfoByName */
    int UpdUserInfoById(String userid,String username,String userpwd,String useremail);
}
