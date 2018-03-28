<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>公共笔记本</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
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
                <div class="panel panel-primary"></div>
            </div>
            
        </div>
        <div class="row">
        </div>
    </div>
</body>
</html>
<script type="text/javascript">

    //设置 最新更新blog
    function settingNewBlogList(data) {
        var html="";
        $.each(data,function (index,ele) {
            html += "<div><a href='${dynamicUrl}/"+ele.location+"'>"+ele.title+"</a></div>"
        });
        $("#newsblog").html(html);
    }

</script>