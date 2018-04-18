<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>创建博客</title>
	<!-- Bootstrap -->
    <link href="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
    <link rel="stylesheet" type="text/css" href="/resources/bower_components/simditor/styles/simditor.css" />
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <script src="/resources/bower_components/simple-module/lib/module.js"></script>
    <script src="/resources/bower_components/simple-hotkeys/lib/hotkeys.js"></script>
    <script src="/resources/bower_components/simple-uploader/lib/uploader.js"></script>
    <script src="/resources/bower_components/simditor/lib/simditor.js"></script>
    
    <link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/zTree.v3/3.5.33/js/jquery.ztree.all.min.js"></script>
    
    <script src="/resources/js/common.js"></script>
</head>
<body>


<div class="container-fluid">
    <div class="row">
		<#include "../part/head.ftl"/>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1" style="margin-bottom: 100px;">
            <form class="form-horizontal">

                <div class="form-group">
                    <input type="email" class="form-control" id="blog_title" placeholder="标题">
                </div>
                
                <div class="form-group">
                	<input type="hidden" id="groupIds" />
                    <input type="text" id="groupSel"  readonly class="form-control" placeholder="标题" onclick="showMenu();">
                    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
                </div>


                <div class="form-group">
                    <textarea id="editor" placeholder="请输入博客内容……" autofocus ></textarea>
                </div>

                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-lg" onclick="saveBlog(0)">保存</button>
                    <button type="button" class="btn btn-primary btn-lg" onclick="saveBlog(1)">发布</button>
                </div>
            </form>


        </div>
	</div>
	<div class="row">
		<#include "../part/footer.ftl"/>
	</div>
</div>
	<script type="text/javascript">

        var editor;
		$(document).ready(function(){

             editor = new Simditor({
                textarea: $('#editor'),
                //optional options
                toolbar:  ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment']
                ,upload : {
                    url : "/blog/upload", //文件上传的接口地址
                   // params: {"packageId":"12321"}, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                    fileKey: 'file', //服务器端获取文件数据的参数名
                    connectionCount: 3,
                    leaveConfirm: '正在上传文件'
                }
            });

        });

		function saveBlog(status) {
            $.ajax({
                type: "POST",
                url: "/blog/saveBlog",
                data: {
                    "uuid":uuid(),
                    "title":$("#blog_title").val(),
                    "groupIds":$("#groupIds").val(),
                    "status":status,
                    "content":editor.getValue()
                },
                success: function(data){
                    window.location.href="/user/";
                },
                error: function(data){
                    alert("注册失败");
                }
            });
        }

        function uuid() {
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 36; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
            s[8] = s[13] = s[18] = s[23] = "-";

            var uuid = s.join("");
            return uuid;
        }
        
///---------------------------------------------------------        
        var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					pIdKey: "pid"
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};

		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			ids ="";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				ids += nodes[i].id + ",";
			}
			if (v.length > 0 ) {
				v = v.substring(0, v.length-1);
				ids = ids.substring(0, ids.length-1);
				$("#groupIds").val(ids);
			}
			var cityObj = $("#groupSel");
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#groupSel");
			var cityOffset = $("#groupSel").offset();
			$("#menuContent").css({"width": "100%", "z-index":99, "background-color": "#8bc1e6"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "groupSel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.ajax({
	            type: "GET",
	            url: "/group/findAllGroup",
	            success: function(data){
	                //查找分组信息
	                var result = JSON.parse(data);
	                if(result.code == 0){
	                    $.fn.zTree.init($("#treeDemo"), setting, result.data);
	                }else {
	                    result.message;
	                }
	            },
	            error: function(data){
	                alert("获取失败");
	            }
	        });	
		});
        
	</script>
</body>
</html>