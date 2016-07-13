<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页头</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(../images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="../images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="../passon.act" target="rightFrame" class="selected"><img src="../images/icon01.png" title="人员" /><h2>人员查询</h2></a></li>
    <li><a href="../opasson.act" target="rightFrame"><img src="../images/icon02.png" title="订单" /><h2>订单查询</h2></a></li>
    </ul>
    
    <div class="topright">  
   <div class="user">
    <span>${sessionScope.admin}</span>
    <i>已登录</i>
    <i><a href="../index.jsp" target="_parent">退出</a><i>
    </div>        
    </div>
</body>
</html>
