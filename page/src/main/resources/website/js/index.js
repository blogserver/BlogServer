/**
*	博客详细页面的JS
*	v1.0
*/
/**
* 初始化加载
*/
$(function() {
	newBlogs();
	highvisitBlogs();
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
					html += "<p><a href=\"" +BaseHttpsUrl+ "/"+ele.location+"\" target=\"_blank\">"+ele.title+"</a>----------------"+formatDateTime(ele.createtime)+"</p>";
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
* 设置最高访问量Blog
*/
function highvisitBlogs(){
	console.log("开始设置最高访问量blog");
	$.ajax({
		url : ApiServerUrl+"/blog/highVisit",
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				
				console.log("开始设置");
				var html = "";
				$.each(data, function(i,ele){ 
					html += "<p><a href=\"" +BaseHttpsUrl+ "/"+ele.location+"\" target=\"_blank\">"+ele.title+"</a>----------------"+formatDateTime(ele.createtime)+"</p>";
				});
				$("#highvisitblog").html(html);
				
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
