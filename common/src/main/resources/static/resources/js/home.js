$(function() {
	// 头部菜单当行
	// 消息
	$(".topbar-message-link").mouseenter(function() {
		$(".topbar-message-detail").css("visibility", "visible");
	});
	$(".topbar-message-link").mouseleave(function() {
		$(".topbar-message-detail").css("visibility", "hidden");
		$(".topbar-message-detail").mouseenter(function() {
			$(".topbar-message-detail").css("visibility", "visible");
		});
	});
	$(".topbar-message-detail").mouseleave(function() {
		$(".topbar-message-detail").css("visibility", "hidden");
	});
	// 个人中心
	$(".topbar-user-link").mouseenter(function() {
		$(".topbar-user-detail").css("visibility", "visible");
	});
	$(".topbar-user-link").mouseleave(function() {
		$(".topbar-user-detail").css("visibility", "hidden");
		$(".topbar-user-detail").mouseenter(function() {
			$(".topbar-user-detail").css("visibility", "visible");
		});
	});
	$(".topbar-user-detail").mouseleave(function() {
		$(".topbar-user-detail").css("visibility", "hidden");
	});

	//------------------------------分割线------------------------------------------
	
	
	var window_width = $(window).width();
	var window_height = $(window).height();
	// 设置高度
	$(".adminContent").height(window_height - 50);
	$(".adminLeft").width(180);
	$(".adminRight").width(window_width - 180);

	// 详细内容显示,设置为固定高度
	$(".detail-content").css("width", window_width - 200);
	$(".detail-content").css("max-width", window_width - 200);
	$(".detail-content").css("height", window_height - 70);
	$(".detail-content").css("max-height", window_height - 70);

	// 左侧导航最大高度设置
	$(".sidebar-nav").css("height", window_height - 80);
	$(".sidebar-nav").css("max-height", window_height - 80);

	//------------------------------分割线------------------------------------------
	
	
	// 左侧导航折叠或张开
	$(".sidebar-fold").click(function() {
		var sidebarwidth = $(".adminLeft").width();
		if (sidebarwidth > 100) {
			// 防止获取的值不一致，样式有问题
			window_width = $(window).width();
			$(".adminLeft").width("50");
			$(".adminRight").width(window_width - 50);
			// padding值为20 需要去掉
			$(".detail-content").css("width", window_width - 70);
			$(".detail-content").css("max-width", window_width - 70);
			$(".adminRight").css("margin-left", 50);

			$(".sidebar-fold p i").removeClass("fa-outdent");
			$(".sidebar-fold p i").addClass("fa-indent");
			$(".sidebar-nav span").hide();
		} else {
			window_width = $(window).width();
			$(".adminLeft").width("180");
			$(".adminRight").width(window_width - 180);
			$(".detail-content").css("width", window_width - 200);
			$(".detail-content").css("max-width", window_width - 200);
			$(".adminRight").css("margin-left", 180);

			$(".sidebar-fold p i").removeClass("fa-indent");
			$(".sidebar-fold p i").addClass("fa-outdent");
			$(".sidebar-nav span").show();
		}

	});

	// 左侧导航条
	$(".sidebar-nav a").click(function() {
		$(this).parents('li').siblings('li').children('ul').slideUp(100);
		$(this).parents('li').children('ul').slideDown(100);
		$(".sidebar-nav a").removeClass('current');
		$(this).addClass('current');
		$(this).siblings('ul').slideDown(100);
	});

	//------------------------------分割线------------------------------------------
	
	ajaxContent('common/user/userList');
});

/**
 * 加载指定页面 url 页面地址
 */
function ajaxContent(url) {
	$.ajax({
		url : url,
		type : "get",
		success : function(res) {
			$("#ajaxDetailContect").html(res);
		},
		error : function(res) {
			alert("加载内容失败");
		}
	});
}

function logout() {
	$.ajax({
		type : "get",
		url : "logout",
		success : function(data) {
			window.location.reload();
		},
		error : function(data) {
			common_alert("退出失败");
		}
	});
}
