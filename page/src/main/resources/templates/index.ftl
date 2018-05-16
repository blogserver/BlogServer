<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>公共笔记本</title>
	
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
    <div class="container-fluid">
        <div class="row" id="header" style="height:  70px;">
        	this is header
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">最新更新博客</h3>
                    </div>
                    <div class="panel-body">
                        <div id="newsblog"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-primary">
                	<div class="panel-heading">
                        <h3 class="panel-title">访问量最高博客</h3>
                    </div>
                    <div class="panel-body">
                    <div id="highvisitblog"></div>
                    </div>
                </div>
            </div>
        </div>
        <!--页面脚-->
        <div class="row" id="footer">
        	this is footer
        </div>
    </div>
    
        
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
	<!-- blog系统JS -->
	<script src="${staticUrl}/js/common.js"></script>
    <script src="${staticUrl}/js/index.js"></script>
    
</body>
</html>