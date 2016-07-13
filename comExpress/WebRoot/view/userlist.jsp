<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>快递信息后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
     <li><a href="#">首页</a></li>
    <li><a href="#">管理信息</a></li>
    <li><a href="#">人员信息</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li><a href="adduserpass.act"><span><img src="images/t04.png" /></span>增加人员</a></li>
        </ul>
    </div>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>快递员编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>快递员姓名</th>
        <th>联系电话</th>
        <th>常用邮箱</th>
        <th>身份证号码</th>
        <th>是否审核</th>
        <th>目前状态</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach var="usinfo" items="${userinfo}">
  	<tr>
  		<td>${usinfo.uid }</td>
  		<td>${usinfo.name }</td>
  		<td>${usinfo.tel }</td>
  		<td>${usinfo.email }</td>
  		<td>${usinfo.idCard }</td>
  		<c:if test="${usinfo.state == '1' }">
  		<td>正常</td>
  		</c:if>
  		<c:if test="${usinfo.state == '0' }">
  		<td><font color="red">停用</font></td>
  		</c:if>
  		 <td><a href="updateUser.act?id=${usinfo.uid }&st='0'" class="tablelink">可用</a>&nbsp;&nbsp; 
  		     <a href="updateUser.act?id=${usinfo.uid }&st='1'" class="tablelink">停用</a></td>
   		</tr>
  		</c:forEach>   
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pgInfo.curPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="queryUsers.act?pg=${pgInfo.prePage}"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="queryUsers.act?pg=1">1</a></li>
        <li class="paginItem"><a href="queryUsers.act?pg=2">2</a></li>
        <li class="paginItem"><a href="queryUsers.act?pg=3">3</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="queryUsers.act?pg=${pgInfo.nextPage }"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
