/**
*	博客详细页面的JS
*	v1.0
*/
var BaseHttpUrl = "http://"+window.location.host;
var BaseHttpsUrl = "https://"+window.location.host;
var ApiServerUrl = BaseHttpUrl+":9001";

/**
* 初始化加载
*/
$(function() {
	pageHeader();
	userInfo();
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
            +'<a class="navbar-brand" href="'+BaseHttpUrl+'">Brand</a>'
            +'</div>'

            +'<!-- Collect the nav links, forms, and other content for toggling -->'
            +'<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">'
            +'<ul class="nav navbar-nav">'
            +'<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>'
            +'<li><a href="#">Link</a></li>'
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
            +'</ul>'
            +'<form class="navbar-form navbar-left">'
            +'<div class="form-group">'
            +'<input type="text" class="form-control" placeholder="Search">'
            +'</div>'
            +'<button type="submit" class="btn btn-default">Submit</button>'
            +'</form>'
            +'<ul class="nav navbar-nav navbar-right">';
		
		var parthtml;
		var username=getCookie('username');
		if(username!=null && username!=""){
			console.log('Welcome again '+username+'!');
			parthtml ='<li><a id= "headBlog" href="'+BaseHttpsUrl+'/blog/">写博客</a></li>'
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