package com.xzq.commubity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzq.commubity.dto.QuestionDTO;
import com.xzq.commubity.mapper.QuestionMapper;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.Question;
import com.xzq.commubity.model.User;
import com.xzq.commubity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController  {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionMapper questionMapper;
    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(value = "start", defaultValue = "1") int start,
                        @RequestParam(value = "size", defaultValue = "3") int size,
                        HttpServletRequest request
    ){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    System.out.println(user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        PageHelper.startPage(start,size,"id desc");
        List<QuestionDTO> questions = questionMapper.listDTO();
        PageInfo<QuestionDTO> page = new PageInfo<>(questions,3);
        model.addAttribute("questions",questions);
        model.addAttribute("page", page);
        return "index";
    }
}
