<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
    String path_t = request.getServletContext().getRealPath("/upload");
%>
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
  <div class="panel-head"><strong class="icon-reorder"> 歌曲管理 >> 歌曲列表</strong></div>
  <div class="padding border-bottom">  
  	<input type="text" class="input w50" value=""  id="musicName" placeholder="歌曲名称" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<button type="button" class="button border-yellow" onclick="queryMusicInfoByPage(1)"><span class='icon-search'></span>  查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
  
  <button type="button" class="button border-yellow" onclick="window.location.href='MusicAdd.html'"><span class="icon-plus-square-o"></span>&nbsp;&nbsp;新增歌曲</button>
  </div>
  <table class="table table-hover text-center" style="table-layout: fixed;" id="contextinfo">
  
  </table>
  <div style="margin-top:20px;padding-bottom:20px;margin-left:70%;">
	<label id="labeltext"> 1 / 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="button bg-sub" onclick="getPageMusic(1)">首页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(2)">上一页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(3)">下一页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(4)">末页</button>
  </div>
</div>
<script type="text/javascript">
var index_m = 1;
var count_m = 1;
$(function(){
	queryMusicInfoByPage(1);
	
	

});

//分页查询  pageNo-页码  pageSize-条数 type-类型
function queryMusicInfoByPage(pno){
	$.ajax({
		url:"MusicListServlet",
		type:"post",
		data:{"pno":pno,"type":0,"pageSize":10,"musicName":$("#musicName").val(),"songlist":0},
		success:function(msg) {
			var html = "<th width='10%'>ID</th><th width='10%'>歌曲名</th><th width='10%'>歌手</th><th width='20%'>地址</th><th width='35%'>播放</th><th width='15%'>操作</th>";
			$.each( msg.data.datas, function(i,item){
				
				var url = "暂无文件";
				
				if(item.musicUrl != ""){
					url =  item.musicUrl;
				}
				
				
				html += "<tr style='cursor:pointer;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><td>" + item.id +"</td><td >" + item.musicName + "</td><td  overflow: hidden;text-overflow:ellipsis;white-space: nowrap; style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>" + item.musicer + "</td><td  overflow: hidden;text-overflow:ellipsis;white-space: nowrap; style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>" + url + "</td>";
				html += "<td><audio controlsList='nodownload' id='audio' src='upload/"+ url +"' controls='controls'></audio></td><td><div class='button-group'><a class='button border-main' href='javascript:toUpdateHtml("+ item.id +")'><span class='icon-edit'></span> 修改</a><a class='button border-red' href='javascript:void(0)' onclick='return del(" +item.id +",1)'><span class='icon-trash-o'></span> 删除</a></div></td><tr>";
			
				
			});
			document.getElementById("contextinfo").innerHTML = html;
			var pageno = msg.data.pageNo + " / "+msg.data.pageCount+"  &nbsp;&nbsp;&nbsp;&nbsp;总条数：  "+msg.data.rowcounts;
			$("#labeltext").html(pageno);
			
			index_m = msg.data.pageNo;
			count_m = msg.data.pageCount;
			
			$('audio').on('play',function(){    $('audio').not(this).each(function(){        $(this)[0].pause();        $(this)[0].currentTime = 0.0;    });})
			
			
			

		}
   });
}

function getPageMusic(type){
	if(type==1 && index_m != 1){
		queryMusicInfoByPage(1);
	}else if(type==2 && index_m != 1){
		queryMusicInfoByPage(index_m-1);
	}else if(type==3 && index_m != count_m){
		queryMusicInfoByPage(index_m+1);
	}else if(type==4 && index_m != count_m){
		queryMusicInfoByPage(count_m);
	}
}

function toUpdateHtml(id){  
    window.location.href="MusicUpdate.html?id="+id;  
}  

function toQueryHtml(id){  
    window.location.href="MusicQuery.html?id="+id;  
}  


function deleteMusic(id) {
	$.ajax({
		url : "DeleteMusicService",
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
			
			queryMusicInfoByPage(index_m);
		}
	});
}


function del(id,mid){
	if(confirm("您确定要删除吗?")){
		deleteMusic(id);
	}
}

</script>

</body></html>