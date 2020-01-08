package com.xzq.commubity.controller;

import com.xzq.commubity.mapper.QuestionMapper;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.Question;
import com.xzq.commubity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        //出错填的信息也不丢失
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","tag不能为空");
            return "publish";
        }

        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
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
      if(user==null) {
          model.addAttribute("user", user);
          return "redirect:/";
      }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreater(user.getId());
        question.setGetCreate(System.currentTimeMillis());
        question.setGetModified(question.getGetCreate());
        questionMapper.create(question);

        return "redirect:/";
    }
}
