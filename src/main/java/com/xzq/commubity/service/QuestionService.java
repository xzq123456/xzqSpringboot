package com.xzq.commubity.service;

import com.xzq.commubity.dto.QuestionDTO;
import com.xzq.commubity.mapper.QuestionMapper;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.Question;
import com.xzq.commubity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//中间层
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOS=new ArrayList<>(questions.size());
        for (Question question:questions){
            QuestionDTO questionDTO=new QuestionDTO();
            User user = userMapper.findById(question.getCreater());

           /* questionDTO.setCreater(user.getId());
            questionDTO.setTitle(question.getTitle());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setGetCreate(question.getGetCreate());
            questionDTO.setCommentCount(question.getCommentCount());
            questionDTO.setGetModified(question.getGetModified());
            questionDTO.setViewCount(question.getViewCount());
            questionDTO.setLikeCount(question.getLikeCount());
            questionDTO.setId(question.getId());
            questionDTO.setTag(question.getTag());
            */
           //对象的copy 一样的属性
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
