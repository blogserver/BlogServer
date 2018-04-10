/**
*	博客详细页面的JS
*	v1.0
*/
var ApiServerUrl = BaseHttpUrl+":9001";
var blogUserId = $("#blogUserId").val();
var blogUUID = $("#blogUUID").val();
/**
* 初始化加载
*/
$(function() {
	userInfo();
	
	visit();
});

/**
 * 获取页面用户信息
 * 
 * @returns
 */
function userInfo(){
	console.log("博主信息");
	$.ajax({
		url : ApiServerUrl+"/user/info?userId="+blogUserId,
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				$("#left").html(data.loginname);
				blogInfo();
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
 * 访问记录保存
 * 
 * @returns
 */
function visit(){
    console.log("访问历史");
    $.ajax({
            url : ApiServerUrl+"/blog/visit",
            dataType: 'json',
            type : "post",
            data :{
					"username":getCookie('username'),
                    "url":window.location.href,
                    "title":$(document).attr("title")
            },
            success : function(result) {
                    if(result.code == 0){
                        console.log(result.message);
                    }else{
                        console.log(result.message);
                    }
            },
            error : function(res) {
                        console.log("请求错误");
            }
    });
}
function blogInfo(){
	console.log("博客信息");
	$.ajax({
		url : ApiServerUrl+"/blog/count?blogUuid="+blogUUID,
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				if(data==null||data==undefined||data==""){
					return;
				}
				$("#left").append("<p>访问"+data.visit+"<br/>喜欢"+data.like+"<br/>讨厌"+data.hate+"<br/>收藏"+data.collect+"</p>");
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