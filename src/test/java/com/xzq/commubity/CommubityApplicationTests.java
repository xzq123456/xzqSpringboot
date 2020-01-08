package com.xzq.commubity;

import com.xzq.commubity.dto.QuestionDTO;
import com.xzq.commubity.mapper.QuestionMapper;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.Question;
import com.xzq.commubity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommubityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Test
    void contextLoads() {
//        User byId = userMapper.findById(103);
//        System.out.println(byId);
        List<Question> list = questionMapper.list();
        System.out.println(list);
    }

    @Test
    public void test2(){
        List<QuestionDTO> questionDTOS = questionMapper.listDTO();
        System.out.println(questionDTOS.get(0));
    }
}
