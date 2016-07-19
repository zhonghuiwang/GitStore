<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员查询</title>
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
    <li><a href="#">人员查询</a></li>
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
    <form action="userSelect.act" method="post" name="f1">
    <li><select name="select" class="select3">
    <option value="1">人员编号</option>
    <option value="2">人员姓名</option>
    </select>
    </li>
    <li><input name="search" type="text" class="scinput"/></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>
    </form>
    </ul>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>快递员编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>快递员姓名</th>
        <th>联系电话</th>
        <th>常用邮箱</th>
        <th>身份证号码</th>
        <th>目前状态</th>
        </tr>
        </thead>
        <tbody>
        	<c:forEach var="usinfo" items="${selectinfo}">
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
   		</tr>
  		</c:forEach>   
        </tbody>
    </table>
    <div class="pagin">
    	<div class="message">共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pgInfo.curPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="userSelect.act?pg=${pgInfo.prePage}&search=${text}&select=${key}"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="userSelect.act?pg=1&search=${text}&select=${key}">1</a></li>
        <li class="paginItem"><a href="userSelect.act?pg=2&search=${text}&select=${key}">2</a></li>
        <li class="paginItem"><a href="userSelect.act?pg=3&search=${text}&select=${key}">3</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="userSelect.act?pg=${pgInfo.nextPage }&search=${text}&select=${key}"><span class="pagenxt"></span></a></li>
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
