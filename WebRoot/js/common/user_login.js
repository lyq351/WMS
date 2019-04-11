$().ready(function(){
	//处理提交按纽图片
	$("#submitBtn").mouseover(function(){
		this.src = "img/login_submitBtn2.gif";
	}).mouseout(function(){
		this.src = "img/login_submitBtn1.gif";
	}).click(function() {
		submitForm();
	});
	
	//定位验证码图片框
	var oVCode = $("input[name=vcode]");
	var pos = getAbsPosition(oVCode);
	
	var left = pos.left;
	var top = pos.top + oVCode[0].offsetHeight + 2;
	
	$("div.validateCodeDiv").css("top", top).css("left", left);
	
	//设置验证码图片框显隐
	showHideVcodeImg();
	
	//绑定验证码图片框单击事件实现更换验证码
	$("div.validateCodeDiv").click(function(event) {
		changeCode();
		event.stopPropagation();
	});
});


function submitForm() {
	//表单验证
	var oAcc = $("input[name=em.userName]");
	if(oAcc.val().trim().length == 0) {
		$("#messBox").html("请输入用户名");
		oAcc.focus();
		return;
	}
	var oPass = $("input[name=em.pwd]");
	if(oPass.val().trim().length == 0) {
		$("#messBox").html("请输入密码");
		oPass.focus();
		return;
	}
	var oVcode = $("input[name=vcode]");
	if(oVcode.val().trim().length == 0) {
		$("#messBox").html("请输入验证码");
		oVcode.focus();
		return;
	}
	
		$("form:first").submit();
	
	
}

//显示验证码图片
function showHideVcodeImg() {
	$("input[name=em.userName], input[name=em.pwd], #submitBtn").click(function(){
		$("div.validateCodeDiv").css("display", "none");
	}).focus(function() {
		$("div.validateCodeDiv").css("display", "none");
	});
	
	$("input[name=vcode]").click(function(event) {
		$("div.validateCodeDiv").css("display", "block");
		event.stopPropagation();
	}).focus(function() {
		$("div.validateCodeDiv").css("display", "block");
	});
	
	$(document).click(function() {
		$("div.validateCodeDiv").css("display", "none");
	})
}

//改变验证码
function changeCode() {
	$("#imgVcode").attr("src", "servlet/getVcode?ts=" + new Date().getTime()); //加一个参数使得每次都会变，session有缓存
}
