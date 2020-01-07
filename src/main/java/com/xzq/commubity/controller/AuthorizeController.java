package com.xzq.commubity.controller;

import com.xzq.commubity.dto.AccesstokenDTO;
import com.xzq.commubity.provider.GithubProvider;
import com.xzq.commubity.provider.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,@RequestParam("state") String state) throws Exception {
        //ctrl+alt+v （快速创建 new xxx()）
        //shift+enter 光标迅速切换到下一行 无论光标在上一行的哪一个位置
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_url("http://localhost:8887/callback");
        accesstokenDTO.setClient_id("408a044d31e9e75534e2");
        accesstokenDTO.setClient_secret("6200eeeafd755c1fc9175d430c9c7a1c29bfc33c");
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }
}
