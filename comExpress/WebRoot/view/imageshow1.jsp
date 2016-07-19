<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片原图</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script> 


</head>


<body style="background:#edf6fa;">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="main.jsp">首页</a></li>
    <li><a href="../queryOrders.act">订单查询</a></li>
    <li><a href="#">订单原图</a></li>
    </ul>
    </div>
    
   
    <%
    	String id = request.getParameter("id");
    	String msg = request.getParameter("msg");
     %>
    <div style="width:100%;height:20px;">
     <div style="width:50%;height:20px;float:left"><b>订单号：<%=id %></b></div>
    </div>
    <a href="../opasson.act" target="rightFrame">
    <img src="../<%=msg %>" alt="订单原图还未上传，如若急需请联系快递员" width="100%" height="100%">
    </a>
    
    
</body>
</html>
