package com.opensource.soft.BlogServer.user.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "userblog")
public class UserProperties {
	
	private String apiServerUrl;
	
	private String staticUrl;
	
	private String webSite;

	private String pageServerUrl;
	
	public String getApiServerUrl() {
		return apiServerUrl;
	}

	public void setApiServerUrl(String apiServerUrl) {
		this.apiServerUrl = apiServerUrl;
	}

	public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPageServerUrl() {
		return pageServerUrl;
	}

	public void setPageServerUrl(String pageServerUrl) {
		this.pageServerUrl = pageServerUrl;
	}

}
