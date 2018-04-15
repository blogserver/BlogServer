/**
*	博客分组搜索页面的JS
*	v1.0
*/

var ApiServerUrl = BaseHttpUrl+":9001";

/**
* 初始化加载
*/
$(function() {
	
	$.ajax({
		url : ApiServerUrl+"/group/findAllGroup",
		type : "get",
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code == 0){
				$.fn.zTree.init($("#treeDemo"), setting, result.data);
			}
		},
		error : function(res) {
			alert("加载内容失败");
		}
	});

});


var setting = {
	view: {
		showTitle: true,
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true,
		showRemoveBtn: true,
		showRenameBtn: true
	},

	data: {
		key: {
			title: "description"
		},
		simpleData: {
			enable: true,
			pIdKey: "pid"
		}
	},
	callback: {
		beforeDrag: false,
		beforeEditName: beforeEditName,
		beforeRemove: beforeRemove
	}
};


function beforeEditName(treeId, treeNode) {
	var node = treeNode.getParentNode();
	$("#fgroupid").val(node.id);
	$("#fgroupName").val(node.name);
	$("#groupid").val(treeNode.id);
	$("#groupName").val(treeNode.name);
	$('#myModal').modal('show');
	return true;
}
function beforeRemove(treeId, treeNode) {
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		$("#fgroupid").val(treeNode.id);
		$("#fgroupName").val(treeNode.name);
		$("#groupid").val("");
		$("#groupName").val("");
		$('#myModal').modal('show');
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};
