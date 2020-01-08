package com.xzq.commubity.controller;

import com.xzq.commubity.dto.AccesstokenDTO;
import com.xzq.commubity.mapper.UserMapper;
import com.xzq.commubity.model.User;
import com.xzq.commubity.provider.GithubProvider;
import com.xzq.commubity.provider.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    //引用properties文件定义的变量
    @Value("${github.redirect_url}")
    private String url;
    @Value("${github.client.id}")
    private String id;
    @Value("${github.secret}")
    private String secret;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state,
                           HttpServletRequest request, HttpServletResponse response
                           ){
        //ctrl+alt+v （快速创建 new xxx()）
        //shift+enter 光标迅速切换到下一行 无论光标在上一行的哪一个位置
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_url(url);
        accesstokenDTO.setClient_id(id);
        accesstokenDTO.setClient_secret(secret);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser githubUser= githubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGtmModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            System.out.println(user);
//            插入数据库的过程相当于写入session（JsessionID 用random生成的）
            userMapper.insert(user);
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(1000);
            response.addCookie(cookie);

            //登录成功，写cookie和session
            //会自动把信息给浏览器的cookie
//            request.getSession().setAttribute("user",githubUser);
//            redirect是跳转的目录
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}
