package com.opensource.soft.BlogServer.user.common.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.opensource.soft.BlogServer.user.common.freemarker.MyFreeMarkerView;

@Configuration
public class FreeMarkerConfig {
	  
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
    	FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
    	freeMarkerViewResolver.setSuffix(".ftl");
    	freeMarkerViewResolver.setExposeRequestAttributes(true);
    	freeMarkerViewResolver.setExposeSessionAttributes(true);
    	freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
    	freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
    	freeMarkerViewResolver.setViewClass(MyFreeMarkerView.class);
    	return freeMarkerViewResolver;
    }

}
