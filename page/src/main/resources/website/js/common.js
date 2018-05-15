/**
*	博客详细页面的JS
*	v1.0
*/
var BaseHttpsUrl = "https://"+window.location.host;
var ApiServerUrl = BaseHttpsUrl+"/api";

/**
* 初始化加载
*/
$(function() {
	pageHeader();
	pageFooter();
});

/**
 * 设置页面首部
 * @returns
 */
function pageHeader(){
	var html = '<nav class="navbar navbar-default">'
            +'<div class="container-fluid">'
            +'<!-- Brand and toggle get grouped for better mobile display -->'
            +'<div class="navbar-header">'
            +'<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">'
            +'<span class="sr-only">Toggle navigation</span>'
            +'<span class="icon-bar"></span>'
            +'<span class="icon-bar"></span>'
            +'<span class="icon-bar"></span>'
            +'</button>'
            +'<a class="navbar-brand" href="'+BaseHttpsUrl+'">Brand</a>'
            +'</div>'

            +'<!-- Collect the nav links, forms, and other content for toggling -->'
            +'<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">'
            +'<ul class="nav navbar-nav">'
            +'<li class="active"><a href="'+BaseHttpsUrl+'/user">控制台 <span class="sr-only">(current)</span></a></li>'
            +'<li class="dropdown">'
            +'<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>'
            +'<ul class="dropdown-menu">'
            +'<li><a href="#">Action</a></li>'
            +'<li><a href="#">Another action</a></li>'
            +'<li><a href="#">Something else here</a></li>'
            +'<li role="separator" class="divider"></li>'
            +'<li><a href="#">Separated link</a></li>'
            +'<li role="separator" class="divider"></li>'
            +'<li><a href="#">One more separated link</a></li>'
            +'</ul>'
            +'</li>'
			+'<li><a href="'+BaseHttpsUrl+'/group.html">分类搜索</a></li>'
            +'</ul>'
            +'<form class="navbar-form navbar-left">'
            +'<div class="form-group">'
            +'<input type="text" class="form-control" id="searchContent" placeholder="Search">'
            +'</div>'
            +'<button type="button" onclick="searchBtn()" class="btn btn-default">Submit</button>'
            +'</form>'
            +'<ul class="nav navbar-nav navbar-right">';
		
		var parthtml;
		var username=getCookie('username');
		if(username!=null && username!=""){
			console.log('Welcome again '+username+'!');
			parthtml ='<li><a id= "headBlog" href="'+BaseHttpsUrl+'/user/blog/create">写博客</a></li>'
            +'<li class="dropdown">'
            +'<a href="#" class="dropdown-toggle" id="headLoginName" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">'+username+'</a>'
            +'<ul class="dropdown-menu">'
            +'<li><a href="#">Action</a></li>'
            +'<li><a href="#">Another action</a></li>'
            +'<li><a href="#">Something else here</a></li>'
            +'<li role="separator" class="divider"></li>'
            +'<li><a onclick="logout()" href="'+BaseHttpsUrl+'/user/logout">退出</a></li>'
            +'</ul>'
            +'</li>'
		}else{
			console.log('没有登陆');
			parthtml ='<li><a id = "headRegister" href="'+BaseHttpsUrl+'/user/register">注册</a></li>'
            +'<li><a id = "headLogin" href="'+BaseHttpsUrl+'/user/login">登陆</a></li>'
		}
		
		var endhtml = '</ul>'
            +'</div><!-- /.navbar-collapse -->'
            +'</div><!-- /.container-fluid -->'
            +'</nav>';
	
	$("#header").html(html+parthtml+endhtml);
}

/**
 * 查询按钮
 * @returns
 */
function searchBtn(){
	var q=$("#searchContent").val();
	window.open(BaseHttpsUrl+"/search.html?q="+q);
}

/**
 * 设置页面尾部
 * @returns
 */
function pageFooter(){
	var html = '<nav class="navbar navbar-default navbar-fixed-bottom">'
			   +'<div class="container-fluid">'
			   +'<h4><p style="text-align: center">this is footer</p></h4>'
			   +'</div>'
			   +'</nav>';
	$("#footer").html(html);
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

/**
 * 格式化时间戳
 * @param inputTime
 * @returns
 */
function formatDateTime(inputTime) {
  var date = new Date(inputTime);
  var y = date.getFullYear();
  var m = date.getMonth() + 1;
  m = m < 10 ? ('0' + m) : m;
  var d = date.getDate();
  d = d < 10 ? ('0' + d) : d;
  var h = date.getHours();
  h = h < 10 ? ('0' + h) : h;
  var minute = date.getMinutes();
  var second = date.getSeconds();
  minute = minute < 10 ? ('0' + minute) : minute;
  second = second < 10 ? ('0' + second) : second;
  return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}

/**
 * 获取url参数
 * @param name
 * @returns
 */
function getQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");   
  var r = window.location.search.substr(1).match(reg);   
  if (r != null) return decodeURI(r[2]); return null;  
}
