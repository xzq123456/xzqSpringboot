package com.xzq.commubity;

import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommubityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        User byToken = userMapper.findByToken("25c8861f-0a71-4150-8f9d-7f0ddc3ce874");
        System.out.println(byToken);
    }

}
