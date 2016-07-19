<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加人员</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/IdIdentify.js" charset="UTF-8"></script>
<script>
function unm(){
	var temp=document.getElementById("uid").value;
	var temp2=document.getElementById("uname").value;
	if(temp==""&&temp2==""){
		alert("员工编码和员工姓名不能为空");
		//document.getElementById("msg1").innerHTML="用户名不能为空";
		return false;
	}else{
	   return ture;
	}
}

function upw(){
	var pwd1=document.getElementById("upwd").value;
	var pwd2=document.getElementById("upwd2").value;
	if(pwd1!=pwd2){
		alert("两次输入的密码不一致");
	 //document.getElementById("msg2").innerHTML="两次密码不一致";
	 return false;
	}else{
		return ture;
	}
}
function uem(){
	var um=document.getElementById("email").value;
	if(um.charAt(0)=="@" ||um.charAt(0)=="." || um.charAt(um.length-1)=="@" || um.charAt(um.length-1)=="." || um.indexOf("@")==-1 || um.indexOf(".")==-1){
		alert("请输入正确的邮箱信息");
		//document.getElementById("msg3").innerHTML="邮箱格式不对";
		return false;
	}else{
		return ture;
	}
}

function subs(){
	if(unm() & upw() & uem()){
		return true;
	}else{
		return false;
	}
	
	//return (unm() & upw() & uem());
}
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">管理信息</a></li>
    <li><a href="#">人员信息</a></li>
    <li><a href="#">新增人员</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    <form action="addUsers.act" method="post" name="f1" onsubmit="return subs()">
	    <li><label>员工编号</label><input name="uid" id="uid" type="text" class="dfinput" /><i>员工入职编号</i></li>
	    <li><label>员工姓名</label><input name="name" id="uname" type="text" class="dfinput" onblur="unm()"/><i>与身份证信息保持一致</i></li>
	    <li><label>登录密码</label><input name="pwd" id="upwd" type="password" class="dfinput" /><i>设置员工登录密码</i></li>
		<li><label>确认密码</label><input name="pwd2" id="upwd2" type="password" class="dfinput" onblur="upw()"/><span id="msg2"></span></li>
	    
	    <li><label>证件号码</label><input name="idcard" id="idcard" type="text" class="dfinput" onBlur="javascript:checktheform()"/><i>身份证号码</i></li>
	    
	    <li><label>联系电话</label><input name="tel" type="text" class="dfinput" /></li>
	    <li><label>邮箱地址</label><input name="email" id="email" type="text" class="dfinput" onblur="uem()"/></li>
	    <li><label>&nbsp;</label><input name="usub" type="submit" class="btn" value="确认保存"/></li>
	</form>
	<li>
    <label>&nbsp;</label>
    <c:if test="${msg=='100'}">
    	<font size="5" color="red">保存成功！</font>
    </c:if>
    <c:if test="${msg=='400'}">
    	<font size="5" color="red">保存失败！</font>
   </c:if>
    </li>
    </ul>
    
<%--     	    <li><label>登录密码</label><cite><input name="" type="radio" value="" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="radio" value="" />否</cite></li> --%>
    </div>
   
</body>
</html>
