<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<style type="text/css">
table{

　　

}

td{

　　

}
</style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 用户管理 >> 用户列表</strong></div>
  <div class="padding border-bottom">  
  	<input type="text" class="input w50" value=""  id="username" placeholder="请用户名称" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<button type="button" class="button border-yellow" onclick="queryUserInfoByPage(1)"><span class='icon-search'></span>  查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
  
  <button type="button" class="button border-yellow" onclick="window.location.href='UserAdd.html'"><span class="icon-plus-square-o"></span>&nbsp;&nbsp;新增用户</button>
  </div>
  <table class="table table-hover text-center" style="table-layout: fixed;" id="contextinfo">
  
  </table>
  <div style="margin-top:20px;padding-bottom:20px;margin-left:70%;">
	<label id="labeltext"> 1 / 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="button bg-sub" onclick="getPageUsers(1)">首页</button>
	<button type="button" class="button bg-sub" onclick="getPageUsers(2)">上一页</button>
	<button type="button" class="button bg-sub" onclick="getPageUsers(3)">下一页</button>
	<button type="button" class="button bg-sub" onclick="getPageUsers(4)">末页</button>
  </div>
</div>
<script type="text/javascript">
var index_m = 1;
var count_m = 1;
$(function(){
	queryUserInfoByPage(1);
});

//分页查询  pageNo-页码  pageSize-条数 type-类型
function queryUserInfoByPage(pno){
	$.ajax({
		url:"UserListServlet",
		type:"post",
		data:{"pno":pno,"pageSize":10,"userName":$("#username").val()},
		success:function(msg) {
			var html = "<th width='20%'>ID</th><th width='20%'>用户名</th><th width='20%'>密码</th><th width='15%'>操作</th>";
			$.each( msg.data.datas, function(i,item){ 
				
				html += "<tr style='cursor:pointer;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><td>" + item.id +"</td><td >" + item.userName + "</td><td  overflow: hidden;text-overflow:ellipsis;white-space: nowrap; style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>" + item.passWord + "</td>";
				html += "<td><div class='button-group'><a class='button border-main' href='javascript:toUpdateHtml("+ item.id +")'><span class='icon-edit'></span> 修改</a><a class='button border-red' href='javascript:void(0)' onclick='return del(" +item.id +",1)'><span class='icon-trash-o'></span> 删除</a></div></td><tr>";
			});
			document.getElementById("contextinfo").innerHTML = html;
			var pageno = msg.data.pageNo + " / "+msg.data.pageCount+"  &nbsp;&nbsp;&nbsp;&nbsp;总条数：  "+msg.data.rowcounts;
			$("#labeltext").html(pageno);
			
			index_m = msg.data.pageNo;
			count_m = msg.data.pageCount;
		}
   });
}

function getPageUsers(type){
	if(type==1 && index_m != 1){
		queryUserInfoByPage(1);
	}else if(type==2 && index_m != 1){
		queryUserInfoByPage(index_m-1);
	}else if(type==3 && index_m != count_m){
		queryUserInfoByPage(index_m+1);
	}else if(type==4 && index_m != count_m){
		queryUserInfoByPage(count_m);
	}
}

function toUpdateHtml(id){  
    window.location.href="UserUpdate.html?id="+id;  
}  

function toQueryHtml(id){  
    window.location.href="NewsQuery.html?id="+id;  
}  


function deleteUsers(id) {
	$.ajax({
		url : "DeleteUserService",
		type : "post",
		data:{"id":id},
		dataType : "text",
		success : function(msg) {
			if(msg!=null){
				if (msg=="2") {
					alert("删除成功");
					
				}else{
					alert("删除失败");
				}
			}
			
			
			queryUserInfoByPage(index_m);
		}
	});
}


function del(id,mid){
	if(confirm("您确定要删除该用户并清空该用户所有数据？")){
		deleteUsers(id);
	}
}

</script>

</body></html>