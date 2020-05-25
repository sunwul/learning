package com.sunwul.userinfo.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

/*****
 * @author: Sunwul
 * @date: 2019/12/30 18:40
 * PS: 用户类
 */
public class UserInfo {
    /**用户id通过工具类生成*/
    private String userid;
    /**@NotBlank
     * 验证注解的元素值（不能为null,且去除两端空格后长度大于0）,
     * 不同于@NotEmpty,@NotBlank只应用于字符串且在比较时会去除字符串的空格
     * groups  调用校验接口
     * */
    @NotBlank(message= "用户名不能为空",groups = {UsernameCheck.class})
    private String username;

    @NotBlank(message = "密码不能为空",groups = {UserpwdCheck.class})
    private String userpwd;

    @NotBlank(message = "邮箱不能为空",groups = {UseremailCheck.class})
    private String useremail;

    public String getUseremail() {
        return useremail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * 数据校验接口
     * UsernameCheck  用户名校验
     * UserpwdCheck  用户密码校验
     * UseremailCheck  邮箱校验 --需前台校验格式
     * */
    public interface UsernameCheck{

    }

    public  interface UserpwdCheck{

    }

    public interface UseremailCheck{

    }
    /**
     * 此处的GroupSequence是根据目前的先后顺序进行验证，当存在一个验证不通过的情况则不会验证后一个分组
     * 直接返回验证错误信息，验证顺序是UsernameCheck--》UserpwdCheck--》Default
     * 关于Default，此处springvalidation默认生成的验证接口，验证的范围是所有带有验证信息的属性，
     * 若是属性上方写了验证组，则是验证该组内的属性
     * 若是验证实体类类上写了GroupSequence({}) 则说明重写了Default验证接口，Default就
     * 按照GroupSequence里所写的组信息进行验证
     * */

    /**登录验证顺序*/
    @GroupSequence({UsernameCheck.class, UserpwdCheck.class})
    public interface UserLoginCheckSequence {

    }

    /**注册验证顺序*/
    @GroupSequence({UsernameCheck.class, UserpwdCheck.class, UseremailCheck.class})
    public interface UserSiginCheckSequence {

    }
}
