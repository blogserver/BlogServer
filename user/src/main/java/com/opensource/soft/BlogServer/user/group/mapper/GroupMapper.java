package com.opensource.soft.BlogServer.user.group.mapper;

import com.opensource.soft.BlogServer.user.group.data.Group;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface GroupMapper {

    int insert(Group group);

    Group selectById(Integer id);
    
    List<Group> selectAllGroup();

    int updateById(Group group);
}