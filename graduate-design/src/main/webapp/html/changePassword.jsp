<%--<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
  <%--<meta charset="UTF-8">--%>
  <%--<title>Document</title>--%>
  <%--<link rel="stylesheet" type="text/css" href="../res/static/css/main.css">--%>
  <%--<link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">--%>
  <%--<script type="text/javascript" src="../res/layui/layui.js"></script>--%>
  <%--<script type="text/javascript" src="../res/js/jquery.min.js"></script>--%>
  <%--<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">--%>
  <%--<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">--%>

  <%--<script>--%>
      <%--$.ajax({--%>
          <%--type: "POST",--%>
          <%--url: "${pageContext.request.contextPath}/animal/queryBySale",--%>
          <%--dataType: "JSON",--%>
          <%--success:function (res) {--%>
              <%--for (var i = 0; i < res.length; i++) {--%>
                  <%--var div="<div class=\"item\">\n" +--%>
                      <%--"              <img src=http://192.168.46.138\+res[i].img+ alt=\"\">\n" +--%>
                      <%--"              <div class=\"text\">\n" +--%>
                      <%--"                <div class=\"right-title-number\">"+Number(i)+1+"</div>\n" +--%>
                      <%--"                <div class=\"commod\">\n" +--%>
                      <%--"                  <p>"+res[i].title+"</p>\n" +--%>
                      <%--"                  <span>￥"+res[i].ciurPic+"</span>\n" +--%>
                      <%--"                </div>\n" +--%>
                      <%--"              </div>\n" +--%>
                      <%--"            </div>";--%>
                  <%--$("#footerList").append(div);--%>
              <%--}--%>
          <%--}--%>
      <%--});--%>

     <%--/* $.ajax({--%>
          <%--type:"POST",--%>
          <%--url:"${pageContext.request.contextPath}/user/update",--%>
          <%--data:"user"=--%>
      <%--});*/--%>
  <%--</script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="common/head.jsp"%>--%>
  <%--<div class="content content-nav-base buytoday-content">--%>
    <%--<div id="list-cont">--%>
      <%--<div class="main-nav">--%>
        <%--<%@include file="common/middle.jsp"%>--%>
      <%--</div>--%>
      <%--<div class="banner-box">--%>
        <%--<div class="banner"></div>--%>
      <%--</div>--%>
      <%--<div class="product-list-box">--%>
        <%--<div class="product-list w1200">  --%>
          <%--<div class="tab-title">--%>
            <%--<a href="javascript:;" data-content="yukao">个人信息</a>--%>
          <%--</div>--%>
          <%--<div class="list-cont" cont = 'tuangou'>--%>
            <%--<div class="item-box layui-clear">--%>
              <%--<table id="info" align="center"  style="font-size: 25px;align-content: center;font-family: 华文行楷">--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="姓名" style=""></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.username}"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="电话"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.phone}" readonly></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="性别"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.sex}"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="真实姓名"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.realName}" readonly></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="注册日期"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.registDate}" readonly></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="用户状态"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.status}" readonly></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="省份"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.province}"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td><input type="text" value="签名"></td>--%>
                  <%--<td><input type="text" value="${sessionScope.detailUser.sign}"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                  <%--<td colspan="2"><a href=""><button style="align-content: center" id="submitUpdate">提交修改</button></a></td>--%>
                <%--</tr>--%>

              <%--</table>--%>
            <%--</div>--%>
            <%--<div id="demo0" style="text-align: center;"></div>--%>
          <%--</div>--%>
        <%--</div>  --%>
      <%--</div>--%>
      <%--<div class="footer-wrap">--%>
        <%--<div class="footer w1200">--%>
          <%--<div class="title">--%>
            <%--<h3>销量榜单</h3>--%>
          <%--</div>--%>
          <%--<div class="list-box layui-clear" id="footerList">--%>
            <%--&lt;%&ndash;<div class="item">--%>
              <%--<img src=http://192.168.46.138/${animal.animal.img} alt="">--%>
              <%--<div class="text">--%>
                <%--<div class="right-title-number">1</div>--%>
                <%--<div class="commod">--%>
                  <%--<p>宝宝打底衣棉麻透气不起球白色多色可选</p>--%>
                  <%--<span>￥100.00</span>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <%--&ndash;%&gt;--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>


<%--<script>--%>

  <%--layui.config({--%>
    <%--base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录--%>
  <%--}).use(['mm','laypage','jquery'],function(){--%>
     <%--/* var laypage = layui.laypage,$ = layui.$;--%>
     <%--mm = layui.mm;--%>
      <%--laypage.render({--%>
        <%--elem: 'demo0'--%>
        <%--,count: 100 //数据总数--%>
      <%--});*/--%>


      <%--$('body').on('click','*[data-content]',function(){--%>
        <%--$(this).addClass('active').siblings().removeClass('active')--%>
        <%--var dataConte = $(this).attr('data-content');--%>
        <%--$('*[cont]').each(function(i,item){--%>
          <%--var itemCont = $(item).attr('cont');--%>
          <%--console.log(item)--%>
          <%--if(dataConte === itemCont){--%>
            <%--$(item).removeClass('layui-hide');--%>
          <%--}else{--%>
            <%--$(item).addClass('layui-hide');--%>
          <%--}--%>
        <%--})--%>
      <%--})--%>


<%--});--%>
<%--</script>--%>


<%--</body>--%>
<%--</html>--%>




<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
  <script type="text/javascript" src="../res/layui/layui.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/themes/icon.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.easyui.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/toast.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/toast.js"></script>


  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

  <script type="text/javascript">
      function clickme(){
          if($("#searchByLucene").val() != null && $("#searchByLucene").val() != ""){
              location.href="${pageContext.request.contextPath}/html/commodity2.jsp?page=1&pageRows=9&text="+$("#searchByLucene").val();
          }
      }
    $(function(){
        $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
            $("#loginUser").text(res);
        },"JSON");
        $("#registBtn").click(function(){
            var oldPassword = $("#oldPassword").val();
            var newPassword = $("#newPassword").val();
            var newPassword2 = $("#newPassword2").val();
            $.post("${pageContext.request.contextPath}/user/changePassword","oldPassword="+oldPassword+"&newPassword="+newPassword+"&newPassword2="+newPassword2,function(res){
                if(res != "ok"){
                    $('body').toast({
                        position:'fixed',
                        content:res,
                        duration:3000,
                        isCenter:false,
                        background:'rgba(230,0,0,0.5)',
                        animateIn:'bounceIn-hastrans',
                        animateOut:'bounceOut-hastrans'
                    });
                }

                if(res == "ok"){
                    $("#oldPassword").prop("value","");
                    $("#newPassword").prop("value","");
                    $("#newPassword2").prop("value","");
                    $('body').toast({
                        position:'fixed',
                        content:"修改密码成功",
                        duration:3000,
                        isCenter:false,
                        background:'rgba(230,0,0,0.5)',
                        animateIn:'bounceIn-hastrans',
                        animateOut:'bounceOut-hastrans',
                    });
                }
            },"JSON");
        });
    });
  </script>
</head>
<body>

<%@include file="common/head.jsp" %>

<div class="content content-nav-base  login-content">
  <%@include file="common/middle.jsp" %>
  <div class="login-bg">
    <div class="login-cont w1200">
      <div class="form-box">
        <form id="registForm" class="layui-form" action="javascript:void(0)">
          <legend>修改密码</legend>

          <div class="layui-form-item">
            <div class="layui-inline iphone">
              请输入旧密码：
              <div class="layui-input-inline">
                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                <input type="password" name="password" id="oldPassword" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline iphone">
              请输入新密码：
              <div class="layui-input-inline">
                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                <input type="password" name="password" id="newPassword" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline iphone">
              请再次输入新密码：
              <div class="layui-input-inline">
                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                <input type="password" name="username" id="newPassword2" lay-verify="required" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-form-item login-btn">
              <button id="registBtn" type="submit" class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 300px">提交</button>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</div>

<div class="footer">
  <div class="ng-promise-box">
    <div class="ng-promise w1200">
      <p class="text">
        <a class="icon1" href="javascript:;">7天无理由退换货</a>
        <a class="icon2" href="javascript:;">满99元全场免邮</a>
        <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
      </p>
    </div>
  </div>
  <div class="mod_help w1200">
    <p>
      <a href="javascript:;">关于我们</a>
      <span>|</span>
      <a href="javascript:;">帮助中心</a>
      <span>|</span>
      <a href="javascript:;">售后服务</a>
      <span>|</span>
      <a href="javascript:;">宠物资讯</a>
      <span>|</span>
      <a href="javascript:;">关于货源</a>
    </p>
    <p class="coty">母婴商城版权所有 &copy; 2012-2020</p>
  </div>
</div>

<script type="text/javascript">
    layui.config({
        base: '../res/static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['jquery','form'],function(){
        var $ = layui.$,form = layui.form;


        $("#punmRegistClick").click(function() {
            if(!/^1\d{10}$/.test($("#registPhone").val())){
                layer.msg("请输入正确的手机号");
                return false;
            }
            var obj=this;
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/user/obtain",
                data:"phone="+$("#registPhone").val(),
                dataType:"json",//返回的
                success:function(data) {

                    if(data.result){
                        $("#punmRegistClick").addClass(" layui-btn-disabled");
                        $('#punmRegistClick').attr('disabled',"true");
                        settime(obj);
                        $("#msg").text(data.msg);
                    }else{
                        layer.msg(data.msg);
                    }
                },
                error:function(msg) {
                    console.log(msg);
                }
            });
        })
        var countdown=60;
        function settime(obj) {
            if (countdown == 0) {
                obj.removeAttribute("disabled");
                obj.classList.remove("layui-btn-disabled")
                obj.value="获取验证码";
                countdown = 60;
                return;
            } else {

                obj.value="重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function() {
                    settime(obj) }
                ,1000)
        }
    })

</script>

</body>
</html>