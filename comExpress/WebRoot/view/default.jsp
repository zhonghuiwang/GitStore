<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<% String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>默认页</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="../images/sun.png" alt="天气" /></span>
    <b>${sessionScope.admin }  您好，欢迎使用信息管理系统</b>
    </div>
    
    <div class="welinfo">
    <span><img src="../images/time.png" alt="时间" /></span>
    <i>您本次登录的时间：<%=datetime%></i> 
    </div>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="../images/dp.png" alt="提醒" /></span>
    <b>快递信息管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>您可以快速查看人员信息</span><a href="../queryUsers.act" class="ibtn">人员信息</a></li>
    <li><span>您可以快速查看订单信息</span><a href="../queryOrders.act" class="ibtn">订单信息</a></li>
    </ul>
    </div>
</body>
</html>
