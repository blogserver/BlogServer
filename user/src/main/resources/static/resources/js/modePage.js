/**
 * 初始化mode配置
 */
$(function(){
	$("#model_id").modal({
		  keyboard: false,
		  backdrop: 'static'
		})
	$("#model_id").modal('show');
	$('#model_id').on('hidden.bs.modal', function (e) {
		$("#operateModeDiv").html("");
	});
});