package com.sunwul.redis.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/*****
 * @author: Sunwul
 * @date: 2020/2/25 15:10
 * PS:
 * @JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) 避免Json把懒加载也作为POJO进行序列化
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable {

    private Integer userid;
    private String username;
    private String usersex;

    /**Json默认反序列化调用的是无参数构造函数*/
    public User(){}

    /**注意参数的大小写问题,如果参数名称与前面定义的名称大小写不一致,则可能导致序列化异常,重复向redis中插入数据*/
    public User(Integer userid, String username, String usersex) {
        this.userid = userid;
        this.username = username;
        this.usersex = usersex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", usersex='" + usersex + '\'' +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }
}
