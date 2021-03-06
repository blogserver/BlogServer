<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>搜索页面</title>
	
	<!-- Bootstrap -->
    <link href="${staticUrl}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

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
    <div class="container-fluid">
        <div class="row" id="header" style="min-height:  70px;">
        </div>
        <div class="row">
            <div class="col-md-9">
            	<div id="searchKey">
            		<form class="form-horizontal">
					  <div class="form-group">
						<div class="col-sm-8">
						  <input type="text" class="form-control" id="inputSearch">
						</div>
						<div class="col-sm-2">
							<button type="button" onclick="inputSearchBtn()" class="btn btn-default">搜索</button>
						</div>
						
					  </div>
					</form>
            	</div>
            	<div id="searchResult"></div>
            </div>
            <div class="col-md-3">
            </div>
        </div>
        <!--页面脚-->
        <div class="row" id="footer">
        </div>
    </div>
    
        
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${staticUrl}/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${staticUrl}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
   
	<!-- blog系统JS -->
	<script src="${staticUrl}/js/common.js"></script>
    <script src="${staticUrl}/js/search.js"></script>
    
</body>
</html>