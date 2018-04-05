/**
*	博客详细页面的JS
*	v1.0
*/
//var BaseUrl = "http://www.chinaopensource.top";
var BaseUrl = "http://localhost"
var ApiServerUrl = BaseUrl+":9001";

/**
* 初始化加载
*/
$(function() {
	newBlogs();
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

