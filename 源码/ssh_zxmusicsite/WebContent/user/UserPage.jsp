<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/ssh_zxmusicsite/css/pintuer.css">
<link rel="stylesheet" href="/ssh_zxmusicsite/css/admin.css">
<link rel="stylesheet" href="/ssh_zxmusicsite/css/jquery-ui.min.css">
<script src="/ssh_zxmusicsite/js/jquery.js"></script>
<script src="/ssh_zxmusicsite/js/pintuer.js"></script>
<script src="/ssh_zxmusicsite/js/jquery-ui.min.js"></script>
<title>音乐大厅</title>
<style >
	td{
		align:center;
	}
</style>
</head>
<body>
		<div id="div" style="position: absolute;left: 470px;top: 10px;font-size:30px;color:green;">
			<label>音乐大厅</label>
		</div>
		<div id="div111" style="position: absolute;left: 910px;top: 10px;">
			
			<input type="text" class="input w50" style="width:200px;"  name="userName" id="userName" placeholder="用户名"/><input style="width:200px;" type="password" class="input w50" name="passWord" id="passWord" placeholder="密码" style="margin-left:10px;"/> 
			<button class="button button bg-sub" type="button" onclick="login()" style="margin-left:10px; id="btn">登录</button>
			<button class="button button bg-sub" type="button" onclick="zhuce()" style="margin-left:10px; id="btn">注册</button>
								<a href="/ssh_zxmusicsite/login.jsp" target="_blank">管理员登录</a>
		</div>
		<div id="div222"  style="position: absolute;left: 1380px;top: 10px;display: none;font-size:20px;color:red;">
			用户：<label id="dqyh"></label><input type="hidden" id="userid" />
				<a href="/ssh_zxmusicsite/login.jsp" target="_blank">管理员登录</a>
		</div>
	
	
	
	<div style=" width:1000px;height:800px;margin-left:auto;margin-right:auto;margin-top:70px;background-image:url('/ssh_zxmusicsite/images/bg.jpg');">
		<br>
		<div style="margin-left:200px;">
			<input type="text" class="input w50" style="width: 500px;" value=""  id="musicName" placeholder="输入歌曲名称进行查找" />&nbsp;&nbsp;&nbsp;&nbsp;
  			<button type="button" class="button border-yellow" onclick="queryMusicInfoByPage(1)"><span class='icon-search'></span>  查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div style="width:150px;height:600px;float:left;line-height:40px;font-size:15px;color:white;">
		<table id="menu" border="0" cellpadding="0" cellspacing="0" style="margin-left:30px;" >
			<tr>
				<th style="font-size:20px;">菜单列表</th>
			</tr>
			<tr>
		  		<td align="center">电台音乐</td>
		  	</tr>
		  </table>
		  </div>
		  <div style="height:800px;width:1px;float:left;"></div>
	
		<table id="contextinfo" class="table table-hover text-center" style="width:800px;float:left;margin-left:40px;table-layout: fixed;line-height:40px;"  >
			
			
		  </table>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   
	
    <div style="position: absolute;left: 1000px;top: 900px;color:blue;">
	<label id="labeltext"> 1 / 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="button bg-sub" onclick="getPageMusic(1)">首页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(2)">上一页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(3)">下一页</button>
	<button type="button" class="button bg-sub" onclick="getPageMusic(4)">末页</button>
  </div>
</div>
 
<div id="dialog-confirm" style="display: none;" title="请选择收藏的歌单">
	<select  id="shoucangsong">
				
	</select>
</div>
<div id="dialog-confirm-1" style="display: none;" title="添加新歌单">
	<span>歌单名：</span><input id="newname">
</div>

<div id="dialog-confirm-2" style="display: none;" title="修改歌单">
	
	<span>新歌单名：</span><input id="musiclistname">
	
</div>

<div id="dialog-confirm-3" style="display: none;" title="用户注册">
	<br>
	<span>用户名：</span><input id="userName2"><br><br>
	<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" id="passWord2">
	
</div>


</body>
<script type="text/javascript">
	var index_m = 1;
	var count_m = 1;
	$(function(){
		
		queryMusicInfoByPage(1);
		
	})
	
	
	
	var songlist=0;
	function queryMusicInfoByPage(pno){
		$.ajax({
			url:"/ssh_zxmusicsite/MusicListServlet",
			type:"post",
			data:{"songlist":songlist,"pno":pno,"type":0,"pageSize":10,"musicName":$("#musicName").val()},
			success:function(msg) {
				var html = "<thead><th width='10%'>歌曲名</th><th width='10%'>歌手</th><th width='30%'>播放</th><th width='30%'>操作</th></thead>";
				$.each( msg.data.datas, function(i,item){
					
					var url = "暂无文件";
					
					if(item.musicUrl != ""){
						url =  item.musicUrl;
					}
					
					var name = item.musicName;
					if(name.length>10){
						name = item.musicName.substring(0,10)+"..."
					}
					
					html += "<tr style='cursor:pointer;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'><td >" + name + "</td><td  overflow: hidden;text-overflow:ellipsis;white-space: nowrap; style='white-space: nowrap;overflow: hidden;text-overflow: ellipsis;'>" + item.musicer + "</td>";
					if(songlist==0){
						html += "<td><audio src='/ssh_zxmusicsite/upload/"+ url +"' controlsList='nodownload' controls='controls'></audio></td><td><div class='button-group'><a class='button border-main' onclick='xiazai("+ item.id +")' href='/ssh_zxmusicsite/upload/"+ url +"' download><span class='icon-arrow-down'></span> 下载</a><a class='button border-red' href='javascript:void(0)' onclick='shoucang("+ item.id +")'><span class='icon-th'></span> 收藏</a></div></td><tr>";
					}else if(songlist==1){
						html += "<td><audio src='/ssh_zxmusicsite/upload/"+ url +"' controlsList='nodownload' controls='controls'></audio></td><td><div class='button-group'><a class='button border-main' onclick='shanchu("+ item.id +")' ></span> 删除</a></div></td><tr>";
					}else{
						html += "<td><audio src='/ssh_zxmusicsite/upload/"+ url +"' controlsList='nodownload' controls='controls'></audio></td><td><div class='button-group'><a class='button border-main' onclick='xiazai("+ item.id +")' href='/ssh_zxmusicsite/upload/"+ url +"' download><span class='icon-arrow-down'></span> 下载</a><a class='button border-main' onclick='shanchu("+ item.id +")' ></span> 删除</a></div></td><tr>";
					}
					
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
	
	function xiazai(id){
		
		if($("#userid").val()!=""){
			$.ajax({
				url : "/ssh_zxmusicsite/DownloadMusicServlet",
				type : "post",
				data : {"musicID":id,"UserID":$("#userid").val()},
				dataType : "json",
			});
		}
		
	}
	function shanchu(id){
		$.ajax({
			url : "/ssh_zxmusicsite/DeleteMusic",
			type : "post",
			data : {"musicID":id,"musicListID":songlist},
			dataType : "text",
			success : function(msg) {
				if(msg==1){
					alert("删除成功");
					queryMusicInfoByPage(1);
				}
			}
		});
	}
	function login(){
		$.ajax({
			url : "/ssh_zxmusicsite/UserLogin",
			type : "post",
			data : {"userName":$("#userName").val(),"passWord":$("#passWord").val()},
			dataType : "json",
			success : function(msg) {
				if(msg.code == 1 ){
					alert("登录成功");
 					$("#div111").css("display","none");
					$("#div222").css("display","block");
					$("#dqyh").html(msg.data.userName);
					$("#userid").val(msg.data.id);
					getmenu(msg.data.id);
				}else{
					alert("账号或密码错误");
				}
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
	
	function getmenu(id){
		$.ajax({
			url : "/ssh_zxmusicsite/UserMusicListServlet",
			type : "post",
			data : {"userID":id},
			dataType : "json",
			success : function(msg) {
				var str="<tr><th style='font-size:20px;'>菜单列表</th></tr><tr><td align='center' onclick='songlistclick(0)'>电台音乐</td></tr>";
				$.each( msg.data, function(i,item){
					if(i==0){
						str+="<tr><td align='center' onclick='songlistclick("+item.id+")'>"+item.name+"</td></tr>";
					}else{
						str+="<tr><td align='center' onclick='songlistclick("+item.id+")'>"+item.name+"</td><td><span onclick='xiugai("+item.id+",\""+item.name+"\")' class='icon-pencil'></span></td></tr>";
					}
					
					
				});
				str+="<tr><td align='center' onclick='add()'><span class='icon-plus'></span>&nbsp;&nbsp;添加歌单</td></tr>";
				$("#menu").html(str);
			}
		});
	}
	function shoucang(id){
		//弹框选择收藏的歌单
		if($("#userid").val()!=""){
			$.ajax({
				url : "/ssh_zxmusicsite/UserMusicListServlet",
				type : "post",
				data : {"userID":$("#userid").val()},
				dataType : "json",
				success : function(msg) {
					var str1="123";
					$.each( msg.data, function(i,item){
						if(i!=0){
							str1 += "<option value='"+item.id+"'>"+item.name+"</option>";
						}
					});
					$("#shoucangsong").html(str1);
				}
			});
			$( "#dialog-confirm" ).dialog({
			      resizable: false,
			      height:140,
			      modal: true,
			      buttons: {
			        "收藏": function() {
			        	console.log();
			        	$.ajax({
			    			url : "/ssh_zxmusicsite/CollectionMusic",
			    			type : "post",
			    			data : {"musicID":id,"musicListID":$("#shoucangsong").val()},
			    			dataType : "text",
			    			success : function(msg) {
			    				if(msg=="1"){
			    					alert("收藏成功！！！");
			    				}else{
			    					alert("重复收藏！！！");
			    				}
			    				
			    			}
			    		});
			          $( this ).dialog( "close" );
			        },
			        "取消": function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
		}else{
			alert("请登录！！！");
		}
		
	}
	function add(){
		//弹框添加歌单
		$( "#dialog-confirm-1" ).dialog({
		      resizable: false,
		      height:150,
		      modal: true,
		      buttons: {
		        "添加": function() {
		        	$.ajax({
		    			url : "/ssh_zxmusicsite/AddUserMusicList",
		    			type : "post",
		    			data : {"userMusicListName":$("#newname").val(),"userId":$("#userid").val()},
		    			dataType : "text",
		    			success : function(msg) {
		    				if(msg=="1"){
		    					alert("添加成功！！！");
		    					getmenu($("#userid").val());
		    				}
		    			}
		    		});
		          $( this ).dialog( "close" );
		        },
		        "取消": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		    });
	}
	
	function zhuce(){
		$( "#dialog-confirm-3" ).dialog({
		      resizable: false,
		      height:200,
		      modal: true,
		      buttons: {
		        "确定": function() {
		        	$.ajax({
		    			url : "/ssh_zxmusicsite/UpdateAndAddUser",
		    			type : "post",
		    			data : {"type":1,"userName":$("#userName2").val(),"passWord":$("#passWord2").val()},
		    			dataType : "text",
		    			success : function(msg) {
		    				if(msg!=null){
		    					if(msg=="2"){
		    						alert("注册成功")
		    					}else if(msg=="3"){
		    						alert("用户名已存在")
		    					}else if(msg=="1"){
		    						alert("注册失败")
		    					}
		    				}
		    			}
		    		});
		          $( this ).dialog( "close" );
		        },
		        "取消": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		    });
	}
	
	function xiugai(id,name){
		//弹框改名
		
		$("#musiclistname").val(name);
		
		$( "#dialog-confirm-2" ).dialog({
		      resizable: false,
		      height:150,
		      modal: true,
		      buttons: {
		        "修改": function() {
		        	$.ajax({
		    			url : "/ssh_zxmusicsite/UpdateUserMusicList",
		    			type : "post",
		    			data : {"userMusicListName":$("#musiclistname").val(),"userMusicListid":id},
		    			dataType : "text",
		    			success : function(msg) {
		    				if(msg=="1"){
		    					alert("修改成功！！！");
		    					getmenu($("#userid").val());
		    				}
		    			}
		    		});
		          $( this ).dialog( "close" );
		        },
		        "取消": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		    });
	}

	function songlistclick(id){
		songlist=id;
		queryMusicInfoByPage(1);
	}

</script>
</html>

