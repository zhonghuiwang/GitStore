<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户添加</title>
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
    <li><a href="main.jsp">首页</a></li>
     <li><a href="#">用户列表</a></li>
    <li><a href="#">用户添加</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">统计查询</a></li> 
  	</ul>
    </div>   
  	<div id="tab2" class="tabson">
    
    <ul class="seachform">
    <form action="userSelect.act" method="post" name="f1">
    <li><select name="selectY" class="select3">
    <option value="2016">2016年</option>
    <option value="2017">2017年</option>
    <option value="2018">2018年</option>
    <option value="2019">2019年</option>
    <option value="2020">2020年</option>
    <option value="2021">2021年</option>
    <option value="2022">2022年</option>
    <option value="2023">2023年</option>
    <option value="2024">2024年</option>
    <option value="2025">2025年</option>
    <option value="2026">2026年</option>
    </select>
    </li>
    <li><select name="selectM" class="select3">
    <option value="0">全年</option>
    <option value="1">1月</option>
    <option value="2">2月</option>
    <option value="3">3月</option>
    <option value="4">4月</option>
    <option value="5">5月</option>
    <option value="6">6月</option>
    <option value="7">7月</option>
    <option value="8">8月</option>
    <option value="9">9月</option>
    <option value="10">10月</option>
    <option value="11">11月</option>
    <option value="12">12月</option>
    </select>
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
