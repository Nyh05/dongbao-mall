package com.msb.dongbao.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UserMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UserMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author nyh
 * @since 2021-09-20
 */
public interface UmsMemberService extends IService<UmsMember> {

    //public String register(UserMemberRegisterParamDTO userMemberRegisterParamDTO);
    public ResultWrapper register(UserMemberRegisterParamDTO userMemberRegisterParamDTO);

    //public String login(UserMemberLoginParamDTO userMemberLoginParamDTO);
    public ResultWrapper login(UserMemberLoginParamDTO userMemberLoginParamDTO);

    public ResultWrapper edit(UmsMember umsMember);
}
