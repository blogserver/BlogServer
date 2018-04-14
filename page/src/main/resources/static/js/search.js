/**
*	博客搜索页面的JS
*	v1.0
*/
// 
//  使用nginx跳转 解决跨域访问问题
//	location /solr {
//			proxy_pass http://localhost:8983/solr;
//		}
//var SolrServerUrl = BaseHttpUrl+":8983";
/**
* 初始化加载
*/
$(function() {
	$.ajax({
		url : BaseHttpUrl+"/solr/db/select?q=%E5%AE%89%E8%A3%85",
		type : "get",
		success : function(res) {
			alert(res.responseHeader.status);
			alert("返回内容错误");
		},
		error : function(res) {
			alert("加载内容失败");
			
		}
	});
	
});
