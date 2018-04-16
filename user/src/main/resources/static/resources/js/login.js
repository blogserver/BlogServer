$(function() {
	
});

function reloadValidateCode(){
	$("#validateCodeImg").attr("src","validateCode?data="+Math.random());
}

function register() {
    $.ajax({
        type: "POST",
        url: "/user/addUser",
        data: $('#userRegister').serialize(),
        success: function(data){
            var result = JSON.parse(data);
            if(result.code == 0){
                window.location.href="/user/login";
            }else {
                alert(result.message);
            }
        },
        error: function(data){
            alert("注册失败");
        }
    });
}