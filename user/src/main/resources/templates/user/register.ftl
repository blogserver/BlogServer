<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>注册</title>
	<#include "../part/comment.ftl"/>
</head>
<body>

<div class="container-fluid">
    <div class="row">
		<#include "../part/head.ftl"/>
    </div>
    <div class="row">

        <form class="form-horizontal" id="userRegister">
            <div class="form-group">
                <label for="loginname" class="col-sm-3 control-label">登录名</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="loginname" name="loginname" placeholder="登陆名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">密&nbsp;&nbsp;码</label>
                <div class="col-sm-7">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <label for="nickname" class="col-sm-3 control-label">昵&nbsp;&nbsp;称</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="昵称">
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-sm-3 control-label">电&nbsp;&nbsp;话</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" id="phone" name="phone"  placeholder="电话">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-3 control-label">Email</label>
                <div class="col-sm-7">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-3 col-sm-offset-3">
                    <input type="text" class="form-control" id="validateCode" name="validateCode" placeholder="图片验证码">
                </div>
                <div class="col-sm-4">
                    <a href="#" onclick="javascript:reloadValidateCode();"><img alt="图片验证码" id="validateCodeImg"  src="validateCode"></a>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-7">
                    <button type="button"  onclick="register()"  class="btn btn-primary  btn-lg btn-block">注册</button>
                </div>
            </div>
        </form>


    </div>
    <div class="row">
		<#include "../part/footer.ftl"/>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
	function reloadValidateCode(){
		$("#validateCodeImg").attr("src","validateCode?data=" + new Date() + Math.floor(Math.random()*24));
	}

	function register() {
        $.ajax({
            type: "POST",
            url: "/user/addUser",
            data: $('#userRegister').serialize(),
            success: function(data){
                var result = JSON.parse(data);
                if(result.code == 0){
                    window.location.href="/user/login";
                }else {
                    alert(result.message);
                }
            },
            error: function(data){
                alert("注册失败");
            }
        });
    }
</script>