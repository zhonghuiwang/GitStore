<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单列表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
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
    <li><a href="#">订单信息</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li><a href="Passon.act"><span><img src="images/t04.png" /></span>信息统计</a></li>
        </ul>
    
    </div>
    
    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>订单编号</th>
    <th>收件地址</th>
    <th>发件地址</th>
    <th>快递员姓名</th>
    <th>快递员电话</th>
    <th>订单状态</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody align="left">
    <c:forEach var="imageinfo" items="${imageinfo}">
    <tr>
    <td class="imgtd"><img src="${imageinfo.image.image }" width="80px" height="60px"/></td>
    <td>${imageinfo.id }<p>上传时间：${imageinfo.image.year }-${imageinfo.image.month }-${imageinfo.image.date } ${imageinfo.image.time }</p></td>
    <td>${imageinfo.inaddr }</td>
    <td>${imageinfo.outaddr }</td>
    <td>${imageinfo.users.name }<p>ID:${imageinfo.users.uid }</p></td>
    <td>${imageinfo.users.tel }</td>
    <c:if test="${imageinfo.state=='1' }">
  		<td><font color=green>未过期</font></td>
  	</c:if>
 	<c:if test="${imageinfo.state=='0' }">
  		<td><font color=red>已过期</font></td>
  	</c:if>
    <td><a href="updateOrder.act?id=${imageinfo.id }&st='1'" class="tablelink">失效</a>&nbsp;&nbsp;
    <a href="updateOrder.act?id=${imageinfo.id }&st='0'" class="tablelink">有效</a></p></td>
    </tr>
    </c:forEach>
    
    </tbody>
    
    </table>
    
    
    
    
    
   
     <div class="pagin">
    	<div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pgInfo.curPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="queryOrders.act?pg=${pgInfo.prePage}"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="queryOrders.act?pg=1">1</a></li>
        <li class="paginItem"><a href="queryOrders.act?pg=2">2</a></li>
        <li class="paginItem"><a href="queryOrders.act?pg=3">3</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="queryOrders.act?pg=${pgInfo.nextPage }"><span class="pagenxt"></span></a></li>
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
    
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
