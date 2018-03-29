<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>创建博客</title>
    <#include "../part/comment.ftl"/>

    <link rel="stylesheet" type="text/css" href="${staticUrl}/resources/bower_components/simditor/styles/simditor.css" />

    <script src="${staticUrl}/resources/bower_components/simple-module/lib/module.js"></script>
    <script src="${staticUrl}/resources/bower_components/simple-hotkeys/lib/hotkeys.js"></script>
    <script src="${staticUrl}/resources/bower_components/simple-uploader/lib/uploader.js"></script>
    <script src="${staticUrl}/resources/bower_components/simditor/lib/simditor.js"></script>

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
                    <textarea id="editor" placeholder="请输入博客内容……" autofocus ></textarea>
                </div>

                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-lg" onclick="saveBlog()">保存</button>
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
                    url : "http://localhost:1234/img/upload", //文件上传的接口地址
                   // params: {"packageId":"12321"}, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                    fileKey: 'file', //服务器端获取文件数据的参数名
                    connectionCount: 3,
                    leaveConfirm: '正在上传文件'
                }
            });

        });

		function saveBlog() {
            $.ajax({
                type: "POST",
                url: "${dynamicUrl}/blog/saveBlog",
                data: {
                    "uuid":uuid(),
                    "title":$("#blog_title").val(),
                    "content":editor.getValue()
                },
                success: function(data){
                    window.location.href="${dynamicUrl}/user/";
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
	</script>
</body>
</html>