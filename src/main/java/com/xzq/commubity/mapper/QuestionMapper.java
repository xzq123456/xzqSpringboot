package com.xzq.commubity.mapper;

import com.xzq.commubity.dto.QuestionDTO;
import com.xzq.commubity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert(  "insert into question(title,description,get_create,get_modified,creater,comment_count,view_count,like_count,tag) values (#{title},#{description},#{getCreate},#{getModified},#{creater},#{commentCount},#{viewCount},#{likeCount},#{tag})" )
    public void create(Question question);

    public List<Question> list();

    public List<QuestionDTO> listDTO();

    public List<QuestionDTO> listDTOById(@Param("userId") Integer userId);
}
