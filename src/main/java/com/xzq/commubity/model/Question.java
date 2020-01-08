package com.xzq.commubity.model;

import lombok.Data;

@Data
public class Question {
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
}
