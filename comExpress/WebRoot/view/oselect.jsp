<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单查询</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">订单查询</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">查询</a></li> 
  	</ul>
    </div>   
  	<div id="tab2" class="tabson">
    
    <ul class="seachform">
    <form action="orderSelect.act" method="post" name="f1">
    <li><select name="select" class="select3">
    <option value="1">订单编号</option>
    <option value="2">人员编号</option>
    </select>
    </li>
    <li><input name="search" type="text" class="scinput"/></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>
    </form>
    </ul>
    
    
    <table class="tablelist">
    	<thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>订单编号</th>
    <th>收件地址</th>
    <th>发件地址</th>
    <th>快递员姓名</th>
    <th>快递员电话</th>
    <th>订单状态（待开发）</th>
    </tr>
    </thead>
    
    <tbody align="left">
    <c:forEach var="imageinfo" items="${selectinfo}">
    <tr>
    <td class="imgtd">
    <a href="view/imageshow1.jsp?msg=${imageinfo.image.image }&id=${imageinfo.id }"><img src="${imageinfo.image.image }" width="90px" height="80px" alt="该订单图片还未上传"></a>
    </td>
    <td>${imageinfo.id }<p>上传时间：${imageinfo.year }-${imageinfo.month }-${imageinfo.date } ${imageinfo.time }</p></td>
    <td>${imageinfo.inaddr }</td>
    <td>${imageinfo.outaddr }</td>
    <td>${imageinfo.users.name }<p>ID:${imageinfo.users.uid }</p></td>
    <td>${imageinfo.users.tel }</td>
    <c:if test="${imageinfo.state=='1' }">
  		<td><font color=green>未配送</font></td>
  	</c:if>
 	<c:if test="${imageinfo.state=='0' }">
  		<td><font color=red>已配送</font></td>
  	</c:if>
    </tr>
    </c:forEach>
    
    </tbody>
    </table>
    
   <div class="pagin">
    	<div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pgInfo.curPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="orderSelect.act?pg=${pgInfo.prePage}&search=${text}&select=${key}"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="orderSelect.act?pg=1&search=${text}&select=${key}">1</a></li>
        <li class="paginItem"><a href="orderSelect.act?pg=2&search=${text}&select=${key}">2</a></li>
        <li class="paginItem"><a href="orderSelect.act?pg=3&search=${text}&select=${key}">3</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="orderSelect.act?pg=${pgInfo.nextPage }&search=${text}&select=${key}"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
  
    
    </div>  
       
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    
    </div>

</body>
</html>
