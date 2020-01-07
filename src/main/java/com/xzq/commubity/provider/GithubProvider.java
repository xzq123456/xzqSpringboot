package com.xzq.commubity.provider;

import com.alibaba.fastjson.JSON;
import com.xzq.commubity.dto.AccesstokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstokenDTO));
        System.out.println(body);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            // System.out.println(string);
            // access_token=83edf3211c4ab2a50ae5b19a5557df013020a37b&scope=user&token_type=bearer
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);//83edf3211c4ab2a50ae5b19a5557df013020a37b
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accesstoken)
                .build();
        String string = null;
        try {
            Response response = client.newCall(request).execute();
            string = response.body().string();
            System.out.println(string);
            //String转会为java的类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(githubUser);
            return  githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
