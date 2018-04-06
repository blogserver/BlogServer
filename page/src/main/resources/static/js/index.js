/**
*	博客详细页面的JS
*	v1.0
*/
var BaseUrl = window.location.protocol+"//"+window.location.host;
var ApiServerUrl = BaseUrl+":9001";

/**
* 初始化加载
*/
$(function() {
	newBlogs();
    checkCookie();
});
/**
* 设置最新Blog
*/
function newBlogs(){
	console.log("开始设置最新blog");
	$.ajax({
		url : ApiServerUrl+"/blog/news",
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				
				console.log("开始设置");
				var html = "";
				$.each(data, function(i,ele){ 
					html += "<p><a href=\"" +BaseUrl+ "/"+ele.location+"\" target=\"_blank\">"+ele.title+"</a></p>";
				});
				$("#newsblog").html(html);
				
			}else{
				alert("返回内容错误");
			}
		},
		error : function(res) {
			alert("加载内容失败");
			alert(res);
		}
	});
}



/**
*  cookie相关设置
*/
function getCookie(c_name){
	if (document.cookie.length>0){
		c_start=document.cookie.indexOf(c_name + "=");
		if (c_start!=-1){
			c_start=c_start + c_name.length+1;
			c_end=document.cookie.indexOf(";",c_start);
			if (c_end==-1){
				c_end=document.cookie.length;
			}
			return document.cookie.substring(c_start,c_end);
		} 
	}
	return "";
}

function setCookie(c_name,value,expiredays){
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

function checkCookie(){
	username=getCookie('username');
	if(username!=null && username!=""){
		console.log('Welcome again '+username+'!');
	}else{
		console.log('没有登陆');
	}
}