/**
*	博客详细页面的JS
*	v1.0
*/
var ApiServerUrl = BaseHttpUrl+":9001";

/**
* 初始化加载
*/
$(function() {
	userInfo();
});

/**
 * 获取页面用户信息
 * 
 * @returns
 */
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