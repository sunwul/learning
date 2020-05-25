package com.sunwul.userinfo.service.impl;

import com.sunwul.userinfo.entity.UserInfo;
import com.sunwul.userinfo.mapper.UserInfoMapper;
import com.sunwul.userinfo.service.UserInfoService;
import com.sunwul.userinfo.util.NewIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****
 * @author: Sunwul
 * @date: 2020/1/8 14:50
 * PS: 逻辑实现 , 方法重写
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**根据id查  测试用*/
    @Override
    public UserInfo selectUserInfoById(String id){
        return userInfoMapper.SelUserInfoByUserId(id);
    }

    /**当前用户所有信息*/
    @Override
    public UserInfo selectUserInfo(String username,String userpwd){
        return userInfoMapper.SelUserInfo(username,userpwd);
    }

    /**用户名是否存在*/
    @Override
    public String selectUserInfoByName(String username){
        return userInfoMapper.SelUserInfoByName(username);
    }

    /**用户是否存在*/
    @Override
    public int selectUseInfoByNameAndPwd(String username,String userpwd){
        return userInfoMapper.SelUseInfoByNameAndPwd(username,userpwd);
    }

    /**查询所有用户(返回一个UserInfo的集合)  admin*/
    @Override
    public List<UserInfo> findAllUserInfo(){
        return userInfoMapper.FindAllUserInfo();
    }

    /**新增用户*/
    @Override
    public int insertUserInfo(String userid,String username,String userpwd,String useremail){
        /**获取新的ID值  为了使前台获取到id值,应该写在mapper中*/
        NewIdUtil newIdUtil=new NewIdUtil();
        String usernewid= newIdUtil.getNewId();
        return userInfoMapper.InsUserInfo(usernewid,username,userpwd,useremail);
    }

    /**删除用户数据 以userid --admin管理员*/
    @Override
    public int deleteUserInfoById(String userid){
        return userInfoMapper.DelUserInfoById(userid);
    }
    /**删除用户数据 以用户名和密码(密码需二次确认,注销后直接回退至登录页面) --用户本人注销*/
    @Override
    public int deleteUserInfoByNameAndPwd(String username,String userpwd){
        return userInfoMapper.DelUserInfoByNameAndPwd(username,userpwd);
    }
    /**修改用户数据 以用户id*/
    @Override
    public int updateUserInfoById(String userid,String username,String userpwd,String useremail){
        return userInfoMapper.UpdUserInfoById(userid,username,userpwd,useremail);
    }
}
