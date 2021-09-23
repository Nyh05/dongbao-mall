package com.msb.dongbao.ums.service.impl;


import com.msb.dongbao.ums.entity.UmsMember;
import com.msb.dongbao.ums.entity.dto.UserMemberLoginParamDTO;
import com.msb.dongbao.ums.entity.dto.UserMemberRegisterParamDTO;
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
public class UmsMemberServiceImpl /*extends ServiceImpl<UmsMemberMapper, UmsMember>*/ implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public String register(UserMemberRegisterParamDTO userMemberRegisterParamDTO){
        UmsMember umsMember=new UmsMember();
        //umsMember.setNickName("cc");
        BeanUtils.copyProperties(userMemberRegisterParamDTO,umsMember);
        umsMemberMapper.insert(umsMember);
        return "success";
    }

    @Override
    public String login(UserMemberLoginParamDTO userMemberLoginParamDTO) {
        /*String username = userMemberLoginParamDTO.getUsername();
        umsMemberMapper.selectByUsername(username);*/
        UmsMember umsMember = umsMemberMapper.selectByUsername(userMemberLoginParamDTO.getUsername());
        if (null!=umsMember){
            String password = userMemberLoginParamDTO.getPassword();
            if (!passwordEncoder.matches(password,umsMember.getPassword())){
                return "用户名密码错误";
            }else {
                return "登录成功";
            }
        }else {
            return "用户不存在";
        }
        //return "success";
    }
}
