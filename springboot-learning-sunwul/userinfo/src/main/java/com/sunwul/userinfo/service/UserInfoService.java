package com.sunwul.userinfo.service;

import com.sunwul.userinfo.entity.UserInfo;

import java.util.List;

/*****
 * @author: Sunwul
 * @date: 2020/1/8 14:49
 * PS: 业务逻辑接口
 */
public interface UserInfoService {

    /**根据id查  测试用*/
    UserInfo selectUserInfoById(String id);

    /**当前用户所有信息*/
    UserInfo selectUserInfo(String name, String pwd);

    /**用户名是否存在*/
    String selectUserInfoByName(String name);

    /**用户是否存在*/
    int selectUseInfoByNameAndPwd(String name, String pwd);

    /**查询所有用户(返回一个UserInfo的集合)  admin*/
    List<UserInfo> findAllUserInfo();

    /**新增用户*/
    int insertUserInfo(String userid, String name, String pwd, String email);

    /**删除用户数据 以userid --admin管理员*/
    int deleteUserInfoById(String userid);

    /**删除用户数据 以用户名和密码(密码需二次确认,注销后直接回退至登录页面) --用户本人注销*/
    int deleteUserInfoByNameAndPwd(String username,String userpwd);

    /**修改用户数据 以用户id*/
    int updateUserInfoById(String userid,String username,String userpwd,String useremail);
}
