package com.opensource.soft.BlogServer.user.common.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class MyFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		model.put("dynamicUrl","http://localhost:8080");
		model.put("staticUrl", "http://localhost:8080");
		super.exposeHelpers(model, request);
	}

}
