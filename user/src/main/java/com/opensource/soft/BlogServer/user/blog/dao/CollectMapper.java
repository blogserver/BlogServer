package com.opensource.soft.BlogServer.user.blog.dao;

import com.opensource.soft.BlogServer.user.blog.model.Collect;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CollectMapper {

    int insert(Collect collect);

    List<Collect> selectByUserOrUUid(Collect collect);

    Collect selectByKey(Collect collect);

    int deleteByKey(Collect collect);
}