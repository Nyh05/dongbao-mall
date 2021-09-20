package com.msb.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserMemberRegisterParamDTO {
    private String username;
    private String password;
    private  String icon;
    private String email;
    private String nickName;
}
