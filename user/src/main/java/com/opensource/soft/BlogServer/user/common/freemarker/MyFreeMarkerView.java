package com.opensource.soft.BlogServer.user.common.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class MyFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
//		model.put("apiServerUrl","http://localhost:9001");
//		model.put("staticUrl","http://localhost");
		model.put("apiServerUrl","http://www.chinaopensource.top:9001");
		model.put("staticUrl","http://www.chinaopensource.top");
		super.exposeHelpers(model, request);
	}

}
