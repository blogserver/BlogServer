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
	//$("#left").html("");
	console.log("博主信息");
	$.ajax({
		url : ApiServerUrl+"/user/info?userId="+blogUserId,
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				var data = result.data;
				$("#left").html('<div>'+data.loginname+'</div><button class="btn btn-sm btn-default" onclick="followBtn()" type="submit">关注</button>');
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



//-----------------  用户操作  ---------------
/**
*	点击喜欢
*/
function likeBtn(){
	console.log("点击喜欢事件");
	if(!checkLogin())return;
	likeUserServer(0);
}


/**
*	点击讨厌
*/
function hateBtn(){
	console.log("点击讨厌事件");
	if(!checkLogin())return;
	likeUserServer(1);
}

/**
*	点击收藏
*/
function collectBtn(){
	console.log("点击收藏事件");
	if(!checkLogin())return;
	$.ajax({
		url : BaseHttpsUrl+"/blog/collectBlog",
		type : "post",
		data :{
			"bloguuid":blogUUID
		},
		headers:{
			"X-Requested-With":"X-Requested-With"
		},
		xhrFields: {  
                withCredentials: true  
        },
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code != 0){
				console.log(result.message);
				window.open(BaseHttpsUrl);
			}else{
				//刷新信息
				userInfo();
			}
		},
		error : function(res) {
			 console.log(res.status);
			
		}
	});
}

/**
*   检测是否登录
*/
function checkLogin(){
	var username=getCookie('username');
	if(username==null||username==undefined||username==""){
		console.log("用户没有登录，跳转登录页面");
		window.open(BaseHttpsUrl);
		return false;
	}
	return true;
}

function likeUserServer(status){
	$.ajax({
		url : BaseHttpsUrl+"/blog/likeBlog",
		type : "post",
		data :{
			"bloguuid":blogUUID,
			"status":status
		},
		headers:{
			"X-Requested-With":"X-Requested-With"
		},
		xhrFields: {  
                withCredentials: true  
        },
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code != 0){
				console.log(result.message);
				window.open(BaseHttpsUrl);
			}else{
				//刷新信息
				userInfo();
			}
		},
		error : function(res) {
			 console.log(res.status);
			
		}
	});
}


/**
*	关注
*/
function followBtn(){
	console.log("点击关注事件");
	if(!checkLogin())return;
	$.ajax({
		url : BaseHttpsUrl+"/user/followUser",
		type : "post",
		data :{
			"followuserid":blogUserId
		},
		headers:{
			"X-Requested-With":"X-Requested-With"
		},
		xhrFields: {  
                withCredentials: true  
        },
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code != 0){
				console.log(result.message);
				window.open(BaseHttpsUrl);
			}else{
				//刷新信息
				//userInfo();
			}
		},
		error : function(res) {
			 console.log(res.status);
			
		}
	});
}

/**
*	保存评论
*/
function commentSaveBtn(){
	console.log("点击保存评论事件");
	if(!checkLogin())return;
	$.ajax({
		url : BaseHttpsUrl+"/comment/saveComment",
		type : "post",
		data :{
			"bloguuid":blogUUID,
			"content":$("#commentContent").val(),
		},
		headers:{
			"X-Requested-With":"X-Requested-With"
		},
		xhrFields: {  
                withCredentials: true  
        },
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code != 0){
				console.log(result.message);
				window.open(BaseHttpsUrl);
			}else{
				//刷新信息
				//userInfo();
			}
		},
		error : function(res) {
			 console.log(res.status);
			
		}
	});
}
