/**
*	博客分组详情页面的JS
*	v1.0
*/

var ApiServerUrl = BaseHttpUrl+":9001";
var groupid = getQueryString("groupid");
/**
* 初始化加载
*/
$(function() {
	
	gropInfo();
});

function gropInfo(){
	
	$.ajax({
		url : ApiServerUrl+"/group/findGroupById?groupId="+groupid,
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var html ="";
				html +='<h4>'+result.data.name+'</h4><p>'+result.data.description+'</p>';
				$("#groupInfo").html(html);
			}
		},
		error : function(res) {
			alert("加载内容失败");
		}
	});
	
	$.ajax({
		url : ApiServerUrl+"/blog/findByGroupId?groupId="+groupid,
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var html ="";
				$.each(result.data, function(i,ele){ 
					html += "<p><a href=\"" +BaseHttpUrl+ "/"+ele.location+"\" target=\"_blank\">"+ele.title+"</a>----------------"+formatDateTime(ele.createtime)+"</p>";
				});
				$("#newsblog").html(html);
			}
		},
		error : function(res) {
			alert("加载内容失败");
		}
	});
}