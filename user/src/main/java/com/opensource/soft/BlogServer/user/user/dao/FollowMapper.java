package com.opensource.soft.BlogServer.user.user.dao;

import com.opensource.soft.BlogServer.user.user.model.Follow;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FollowMapper {

    int insert(Follow follow);

    List<Follow> selectByUserOrFollowUser(Follow follow);

    Follow selectByKey(Follow follow);

    int deleteByKey(Follow follow);
}