package com.opensource.soft.BlogServer.api.group.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.group.data.Group;

@Component
@Mapper
public interface GroupMapper {

    Group selectById(Integer id);
    
    List<Group> selectAllGroup();

}