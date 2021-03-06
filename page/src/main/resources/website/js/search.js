/**
*	博客搜索页面的JS
*	v1.0
*/
/**
* 初始化加载
*/
$(function() {
	var key = getQueryString("q")
	$("#inputSearch").val(key);
	search(encodeURI(key),0,10);
});

function inputSearchBtn(){
	var key = $("#inputSearch").val();
	var url = BaseHttpsUrl+"/search.html?q=";
	this.location.href=url+key;
}

function search(q,start,rows){
	if(q==null||q==undefined||q==""){
		q="*:*";
	}
	if(start==null||start==undefined||start==""){
		start=0;
	}
	if(rows==null||rows==undefined||rows==""){
		rows=10;
	}

	$.ajax({
		url : BaseHttpsUrl+"/solr/db/select?q="+q+"&start="+start+"&rows="+rows,
		type : "get",
		success : function(res) {
			searchResult(res.response.docs);
		},
		error : function(res) {
			alert("加载内容失败");
		}
	});
}

function searchResult(data){
	var html = "";
	$.each(data,function(index,ele){
		html += "<p><a href=\"" +BaseHttpsUrl+ "/"+ele.location+"\" target=\"_blank\">"+ele.title+"</a>----------------"+ele.updatetime+"</p>";
	});
	$("#searchResult").html(html);
}