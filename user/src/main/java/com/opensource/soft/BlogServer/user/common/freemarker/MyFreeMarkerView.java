package com.opensource.soft.BlogServer.user.common.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class MyFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		model.put("dynamicUrl","https://localhost");
		model.put("staticUrl", "https://localhost");
		super.exposeHelpers(model, request);
	}

}
