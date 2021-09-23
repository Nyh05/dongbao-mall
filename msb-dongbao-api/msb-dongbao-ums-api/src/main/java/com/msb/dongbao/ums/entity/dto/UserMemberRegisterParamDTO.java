package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@ToString
public class UserMemberRegisterParamDTO {
    @NotEmpty(message = "用户名不为空")
    private String username;
    @Size(min=1,max = 6,message = "密码长度在1-6位之间")
    private String password;
    private  String icon;
    @Email(message = "Email格式不正确")
    private String email;
    private String nickName;
}
