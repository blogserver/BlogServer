/**
*	博客分组搜索页面的JS
*	v1.0
*/

var ApiServerUrl = BaseHttpUrl+":9001";
var setting
/**
* 初始化加载
*/
$(function() {
	setZtree();
	setData();
});

function setData(){
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
}

//初始化设置
function setZtree(){
	var username=getCookie('username');
	if(username==null||username==undefined||username==""){
		setting = {
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
				onClick: zTreeOnClick
			}
		};
	}else{
		setting = {
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
				onClick: zTreeOnClick,
				beforeDrag: false,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove
			}
		};
	}
}

function zTreeOnClick(event, treeId, treeNode) {
	window.open(BaseHttpUrl+"/groupdetail.html?groupid="+treeNode.id);
};

function beforeEditName(treeId, treeNode) {
	var node = treeNode.getParentNode();
	//根节点不能修改
	if(node == null) return ;
	$("#fgroupid").val(node.id);
	$("#fgroupName").val(node.name);
	$("#groupid").val(treeNode.id);
	$("#groupName").val(treeNode.name);
	$("#description").val(treeNode.description);
		
	$(".modal-title").html("修改");
	$('#myModal').modal('show');
	return true;
}
function beforeRemove(treeId, treeNode) {
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}

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
		
		$(".modal-title").html("添加");
		$('#myModal').modal('show');
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};


/**
* 清空分组
*/
function groupCleanBtn(){
	$("#fgroupid").val("");
	$("#fgroupName").val("");
	$("#groupid").val("");
	$("#groupName").val("");
	$("#description").val("");
	$('#myModal').modal('hide');
}
/**
*	保存分组
*/
function groupSaveBtn(){
	console.log("点击保存分组事件");
	$.ajax({
		url : BaseHttpsUrl+"/group/saveGroup",
		type : "post",
		data :{
			"id":$("#groupid").val(),
			"name":$("#groupName").val(),
			"description":$("#description").val(),
			"pid":$("#fgroupid").val()
		},
		headers:{
			"X-Requested-With":"X-Requested-With"
		},
		xhrFields: {  
                withCredentials: true  
        },
		success : function(res) {
			var result = JSON.parse(res);
			if(result.code != 0){
				console.log(result.message);
				window.open(BaseHttpsUrl);
			}else{
				groupCleanBtn();
				setData();
			}
		},
		error : function(res) {
			 console.log(res);
		}
	});
}

