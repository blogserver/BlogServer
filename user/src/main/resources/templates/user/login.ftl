<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>登录</title>
	<#include "../part/comment.ftl"/>
</head>
<body>


<div class="container-fluid">
    <div class="row">
		<#include "../part/head.ftl"/>
    </div>
    <div class="row">
    	<div class="col-sm-12">
        <form class="form-horizontal"  action="/user/login" method="post">
	        <#if errMsg??>
	        	<div class="alert alert-danger alert-dismissible fade in" role="alert" id="myAlert">
			        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					  <span aria-hidden="true">&times;</span>
					</button>
			        	<p style="text-align: center;">${errMsg}</p>
			    </div>
	        </#if>
            <div class="form-group">
                <label for="loginname" class="col-sm-3 control-label">登录名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="loginname" name="loginname" placeholder="登陆名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">密&nbsp;&nbsp;码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
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
                <div class="col-sm-offset-3 col-sm-6">
                    <button type="submit"  class="btn btn-primary  btn-lg btn-block">登陆</button>
                </div>
            </div>
		</form>
		</div>
	</div>
    <div class="row">
		<#include "../part/footer.ftl"/>
    </div>
	
</body>
</html>

<script type="text/javascript">

	function reloadValidateCode(){
		$("#validateCodeImg").attr("src","validateCode?data="+Math.random());
	}

</script>