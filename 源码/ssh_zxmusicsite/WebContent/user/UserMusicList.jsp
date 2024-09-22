<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="/ssh_zxmusicsite/css/pintuer.css">
<link rel="stylesheet" href="/ssh_zxmusicsite/css/admin.css">
<script src="/ssh_zxmusicsite/js/jquery.js"></script>
<script src="/ssh_zxmusicsite/js/pintuer.js"></script>
<style type="text/css">
table{

　　

}

td{

　　

}
</style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 在线音乐 >> 歌曲列表</strong></div>
  <div class="padding border-bottom">  
  	<input type="text" class="input w50" value=""  id="musicName" placeholder="歌曲名称" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<button type="button" class="button border-yellow" onclick="queryMusicInfoByPage(1)"><span class='icon-search'></span>  查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
  
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
		url:"/ssh_zxmusicsite/MusicListServlet",
		type:"post",
		data:{"pno":pno,"type":0,"pageSize":10,"musicName":$("#musicName").val()},
		success:function(msg) {
			var html = "<th width='20%'>ID</th><th width='20%'>歌曲名</th><th width='20%'>歌手</th><th width='15%'>操作</th>";
			$.each( msg.data.datas, function(i,item){ 
				html += "<tr style='cursor:pointer;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><td>" + item.id +"</td><td >" + item.musicName + "</td><td  overflow: hidden;text-overflow:ellipsis;white-space: nowrap; style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>" + item.musicer + "</td>";
				html += "<td><div class='button-group'><a class='button border-main' href='javascript:toCollectionHtml("+ item.id +")'><span class='icon-edit'></span> 收藏</a><a class='button border-red' href='javascript:downloadMusic("+ item.id +")'><span class='icon-trash-o'></span> 下载</a></div></td><tr>";
			});
			document.getElementById("contextinfo").innerHTML = html;
			var pageno = msg.data.pageNo + " / "+msg.data.pageCount+"  &nbsp;&nbsp;&nbsp;&nbsp;总条数：  "+msg.data.rowcounts;
			$("#labeltext").html(pageno);
			
			index_m = msg.data.pageNo;
			count_m = msg.data.pageCount;
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

//收藏
function toCollectionHtml(id){  
   alert("收藏");
}  

function toQueryHtml(id){  
    window.location.href="MusicQuery.html?id="+id;  
}  

//下载
function downloadMusic(id) {
	alert("下载");
}

</script>

</body></html>