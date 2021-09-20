package com.msb.dongbao.ums.mapper;


import com.msb.dongbao.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.msb.MsbDongbaoUmsApplication.class)
public class UserMemberTest {
    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Test
    void testInsert(){
        UmsMember umsMember=new UmsMember();
        umsMember.setUsername("xiaohua");
        umsMember.setStatus(0);
        umsMember.setPassword("123456");
        umsMember.setNote("nihaoa");
        umsMember.setNickName("nick");
        umsMember.setEmail("email");

        umsMemberMapper.insert(umsMember);

    }

    @Test
    void testUpdate(){
        UmsMember umsMember=new UmsMember();
        umsMember.setId(2L);
        umsMember.setUsername("xiaohuagengxin");
        umsMember.setStatus(0);
        umsMember.setPassword("1gengxin");
        umsMember.setNote("nihaoa");
        umsMember.setNickName("gengxinnick");
        umsMember.setEmail("email");
        umsMemberMapper.updateById(umsMember);
    }
}
