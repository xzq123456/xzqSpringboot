package com.xzq.commubity.dto;

import com.xzq.commubity.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private  Long getCreate;
    private Long getModified;
    private  Integer creater;//创建人
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;
}
