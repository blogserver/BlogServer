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
	userInfo();
});

function userInfo(){
	console.log("开始设置最新blog");
	$.ajax({
		url : ApiServerUrl+"/user/info?userId="+$("#blogUserId").val(),
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				$("#left").html(data.loginname);
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