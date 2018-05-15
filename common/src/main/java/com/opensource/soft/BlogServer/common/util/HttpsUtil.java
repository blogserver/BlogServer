package com.opensource.soft.BlogServer.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSON;

public class HttpsUtil {
	private static final int conntimeout = 30 * 1000;
	private static final int readtimeout = 30 * 1000;
	private static final String charset = "UTF-8";

	public static String doPostJson(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection con = null;
		// 尝试发送请求
		try {
			con = url.startsWith("https") ? getHttpURLConnection(url)
					: (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(conntimeout);
			con.setReadTimeout(readtimeout);
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			OutputStream out = con.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, charset);
			osw.write(JSON.toJSONString(params));
			osw.flush();
			osw.close();
			out.flush();
			out.close();
			if (con.getResponseCode() == 200) {
				InputStream in = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in, charset));
				String temp;
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
				}
				in.close();
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
				con = null;
			}
		}
		return buffer.toString();
	}

	private static HttpURLConnection getHttpURLConnection(String url) throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new SSLTrustManager();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier((HostnameVerifier) tm);
		return (HttpURLConnection) new URL(url).openConnection();
	}

	private static class SSLTrustManager
			implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager, HostnameVerifier {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		@Override
		public boolean verify(String urlHostName, SSLSession session) { // 允许所有主机
			return true;
		}

	}

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uuid", "a653b92f-3530-43fb-83ce-476c3b5c04da");
		try {
			String abc= HttpsUtil.doPostJson("http://localhost:9011/page/createPage", params);
			System.out.println(abc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
