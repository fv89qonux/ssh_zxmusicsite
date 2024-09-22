<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理中心</title>  
    <link rel="stylesheet" href="/ssh_zxmusicsite/css/pintuer.css">
    <link rel="stylesheet" href="/ssh_zxmusicsite/css/admin.css">
    <script src="/ssh_zxmusicsite/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">

<div class="leftnav">
  <ul style="display:block">
    <li><a href="UserMusicList.jsp" target="right"><span class="icon-caret-right"></span>在线音乐</a></li>
    <li><a href="MusicList.jsp" target="right"><span class="icon-caret-right"></span>我的歌单</a></li>   
  </ul>
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<div class="admin">
  <table class="table" style="width:500px;">
  	<tr>
  		<td>歌曲名</td><td>歌手</td><td>操作</td>
  	</tr>
  		<tr>
  		<td>歌曲名</td><td>歌手</td><td>操作</td>
  	</tr>
  		<tr>
  		<td>歌曲名</td><td>歌手</td><td>操作</td>
  	</tr>
  		<tr>
  		<td>歌曲名</td><td>歌手</td><td>操作</td>
  	</tr>
  </table>
</div>
<div style="text-align:center;">
</div>

</body>
</html>