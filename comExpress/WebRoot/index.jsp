<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>       
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="queryAdminById.act" method="post" name="f1">
    <ul>
    <li><input name="uid" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
    <li><input name="pwd" type="text" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li><input name="submit" type="submit" class="loginbtn" value="登录"/>
    <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label></li>
    </ul>
    </form>
    <div>
    <%
   	String msg = request.getParameter("msg");
   	if("400".equals(msg)){
   		out.println("<font color='red'>&nbsp&nbsp&nbsp&nbsp账号信息错误，请重新登录</font>");
   	}
   	if("1".equals(msg)){
   		out.println("<font color='red'>&nbsp&nbsp&nbsp&nbsp您还未登录，请登录</font>");
   	}
    %>
    </div>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2016 浙江</div>
</body>
</html>
