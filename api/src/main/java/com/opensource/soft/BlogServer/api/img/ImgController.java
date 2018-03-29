package com.opensource.soft.BlogServer.api.img;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opensource.soft.BlogServer.api.common.property.ApiProperties;
import com.opensource.soft.BlogServer.common.util.PathUtil;

@RestController
@RequestMapping("/img")
public class ImgController {

	@Autowired
	private ApiProperties blog;

	// 单文件上传
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {

		try {
			if (file.isEmpty()) {
				return "{\"success\":\"" + false + "\"}";
			}
			// 获得文件后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String path = "img" + File.separator + PathUtil.imgPath() + suffix;
			// 设置文件存储路径
			File dest = new File(blog.getWebSite() + path);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();// 新建文件夹
			}
			file.transferTo(dest);// 文件写入

			String urlPath = path.replace("\\", "/");
			return "{\"success\":\"" + true + "\",\"file_path\":\"" + blog.getWebUrl() + "/" + urlPath + "\"}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"success\":\"" + false + "\"}";
	}

}
