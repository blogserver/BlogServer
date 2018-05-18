<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>${blog.title}</title>
	
	<!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- blog系统css -->
    <link href="${staticUrl}/css/detail.css" rel="stylesheet">
    
</head>
<body>
	<!--页面内容-->
    <div class="container-fluid">
    	<!--页面头部-->
        <div class="row" id="header" style="min-height:  70px;">
        </div>
        <!--页面中心内容-->
        <div class="row">
        	<!--左侧导航-->
        	<div class="col-md-2 col-xs-3" id="left">
        		<input type="hidden" id = "blogUserId" value="${blog.createuser}">
        		<input type="hidden" id = "blogUUID" value="${blog.uuid}">
        	</div>
        	<!--博客详细内容-->
        	<div class="col-md-8 col-xs-6" id="center">
        		<h3>${blog.title}</h3>
        		<h5>创建时间:${blog.createtimestr}</h5>
	        	${blog.content}
	        	
	        	<div id="optionBtn">
					<button onclick="likeBtn()" type="button" class="btn btn-default ">
					  <span class="glyphicon glyphicon-heart" aria-hidden="true"></span> 喜欢
					</button>
					<button onclick="hateBtn()"type="button" class="btn btn-default ">
					  <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span> 讨厌
					</button>
					<button onclick="collectBtn()" type="button" class="btn btn-default ">
					  <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> 收藏
					</button>				
				</div>
				<h4>评论</h4>
	        	<form class="form-horizontal">
				  <div class="form-group">
					  <div class="col-md-12 col-xs-12">
						<textarea id="commentContent" class="form-control" rows="5"></textarea>
					  </div>
				  </div>
				  <div class="form-group">
						<div class="col-md-9 col-xs-8">
						</div>
						<div class="col-md-3 col-xs-4">
						  <button onclick="commentCancelBtn()" type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
						  </button>
						  <button onclick="commentSaveBtn()" type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>保存
						  </button>
						</div>
				  </div>
				</form>
				<h4>评论列表</h4>
				<div id="commentList">
				</div>
        	</div>
        	<!--右侧导航-->
        	<div class="col-md-2 col-xs-3" id="right">右侧</div>
        </div>
        <!--页面脚-->
        <div class="row" id="footer">
        </div>
    </div>
    
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!-- blog系统JS -->
    <script src="${staticUrl}/js/common.js"></script>
    <script src="${staticUrl}/js/detail.js"></script>
    
</body>
</html>

<!--登录框-->
<div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog  modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示框</h4>
      </div>
      <div class="modal-body">
        <p>您还没有登录，请确认是否登录</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="goLoginPage()">登录</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->