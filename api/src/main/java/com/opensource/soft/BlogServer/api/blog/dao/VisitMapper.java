package com.opensource.soft.BlogServer.api.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.blog.model.Visit;

@Component
@Mapper
public interface VisitMapper {

    int insert(Visit visit);

}