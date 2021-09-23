package com.msb.dongbao.ums.service.impl;


import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.result.ResultWrapper;
import com.msb.dongbao.common.utill.JwtUtil;
import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UserMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UserMemberRegisterParamDTO;
import com.msb.dongbao.ums.entity.response.UserMemberLoginResponse;
import com.msb.dongbao.ums.mapper.UmsMemberMapper;
import com.msb.dongbao.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author nyh
 * @since 2021-09-20
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
   /* @Override
    public String register(UserMemberRegisterParamDTO userMemberRegisterParamDTO){
        UmsMember umsMember=new UmsMember();
        //umsMember.setNickName("cc");
        BeanUtils.copyProperties(userMemberRegisterParamDTO,umsMember);
        umsMemberMapper.insert(umsMember);
        return "success";
    }*/
   @Override
   public ResultWrapper register(UserMemberRegisterParamDTO userMemberRegisterParamDTO){
       UmsMember umsMember=new UmsMember();
       BeanUtils.copyProperties(userMemberRegisterParamDTO,umsMember);
       String newPassword = passwordEncoder.encode(userMemberRegisterParamDTO.getPassword());
       umsMember.setPassword(newPassword);
       umsMemberMapper.insert(umsMember);
       return ResultWrapper.getSuccessBuilder().build();
   }

    /*@Override
        public String login(UserMemberLoginParamDTO userMemberLoginParamDTO) {
            *//*String username = userMemberLoginParamDTO.getUsername();
        umsMemberMapper.selectByUsername(username);*//*
        UmsMember umsMember = umsMemberMapper.selectByUsername(userMemberLoginParamDTO.getUsername());
        if (null!=umsMember){
            String password = userMemberLoginParamDTO.getPassword();
            if (!passwordEncoder.matches(password,umsMember.getPassword())){
                return "用户名密码错误";
            }
        }else {
            return "用户不存在";
        }
        String token = JwtUtil.CreatToken(umsMember.getUsername());
        return token;
    }*/
    @Override
    public ResultWrapper login(UserMemberLoginParamDTO userMemberLoginParamDTO) {
        UmsMember umsMember = umsMemberMapper.selectByUsername(userMemberLoginParamDTO.getUsername());
        if (null!=umsMember){
            String password = userMemberLoginParamDTO.getPassword();
            if (!passwordEncoder.matches(password,umsMember.getPassword())){
                return ResultWrapper.getFailBuilder().code(StateCodeEnum.PASSWORD_ERROR.getCode()).message(StateCodeEnum.PASSWORD_ERROR.getMessage()).build();
            }
        }else {
            return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_EMPTY.getCode()).message(StateCodeEnum.USER_EMPTY.getMessage()).build();
        }
        /*String token = JwtUtil.CreatToken(umsMember.getUsername());
        return ResultWrapper.getSuccessBuilder().data(token).build();//没有使用userMemberLoginResponse来绑定用户，待定*/
        String token = JwtUtil.CreatToken(umsMember.getId()+" ");
        UserMemberLoginResponse userMemberLoginResponse =new UserMemberLoginResponse();
        userMemberLoginResponse.setToken(token);
        //umsMember.setPassword(" ");
        userMemberLoginResponse.setUmsMember(umsMember);
        //return ResultWrapper.getSuccessBuilder().data(token).build();
        return ResultWrapper.getSuccessBuilder().data(userMemberLoginResponse).build();



    }

    //用户修改信息
    @Override
    public ResultWrapper edit(UmsMember umsMember) {
        umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).build();
    }
}
