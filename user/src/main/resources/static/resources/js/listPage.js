/**
 * 表格初始化
 */
$(function(){
	///------------------------------表格全选/反全选------------------------------------------	
	$(".content-table table thead input , .content-table table tfoot input").click(function(){
		if (this.checked) {  
			$(".content-table table input").each(function(){
				 $(this).prop("checked", true); 
			});  
			
        } else {  
        	$(".content-table table input").each(function(){
        		 $(this).prop("checked", false); 
			});  
        }  
		
	});
	$(".content-table table tbody input").click(function(){
		if (this.checked) {  
		//选中
			var allRow = $(".content-table table tbody input").length;
			var checkRow =$(".content-table table tbody input:checked").length;
			if (allRow == checkRow){
				$(".content-table table thead input").prop("checked", true); 
				$(".content-table table tfoot input").prop("checked", true); 
			}
		} else {
		//未选中
			$(".content-table table thead input").prop("checked", false); 
			$(".content-table table tfoot input").prop("checked", false); 
		}
	});
	
})


/**
 * 详细页面中弹出模态框
 */
function showMode(url) {
	$.ajax({
		url : url,
		type : "get",
		success : function(res) {
			$("#operateModeDiv").html(res);
		},
		error : function(res) {
			alert("加载内容失败");
			alert(res);
		}
	});
}

/**
 * 获取表格中，选择框中的CheckBox的值
 * @returns
 */
function getOneChooseRol(){
	var checkRow =$(".content-table table tbody input:checked");
	if(checkRow.length == 0){
		common_alert("请选择一行");
		return -1;
	}else if(checkRow.length > 1){
		common_alert("最多只能选中一行");
		return -1;
	}else {
		return checkRow.val();
	}
}
