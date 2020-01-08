package com.xzq.commubity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzq.commubity.dto.QuestionDTO;
import com.xzq.commubity.mapper.QuestionMapper;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.User;
import com.xzq.commubity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action, Model model, HttpServletRequest request,
                            @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "2") int size
                          ){
        Cookie[] cookies = request.getCookies();
        User user=null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                     user = userMapper.findByToken(token);
                    System.out.println(user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null)
            return "redirect:/";

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            PageHelper.startPage(start,size,"id desc");
            List<QuestionDTO> questions = questionMapper.listDTOById(198);
            PageInfo<QuestionDTO> page = new PageInfo<>(questions,3);
            model.addAttribute("questions2",questions);
            model.addAttribute("page2", page);

        }else if("replyes".equals(action)){
            model.addAttribute("section","replyes");
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
