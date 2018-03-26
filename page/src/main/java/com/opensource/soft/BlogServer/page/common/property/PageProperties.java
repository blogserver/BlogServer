package com.opensource.soft.BlogServer.page.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pageblog")
public class PageProperties {
	
	private String webSite;
	
	private String webUrl;
	
	private boolean cacheftl;

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public boolean isCacheftl() {
		return cacheftl;
	}

	public void setCacheftl(boolean cacheftl) {
		this.cacheftl = cacheftl;
	}

}
