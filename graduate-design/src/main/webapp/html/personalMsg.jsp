<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
        $(function(){
            $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
                $("#loginUser").text(res);
            },"JSON");
            //发送请求拿到当前登录用户的信息返回来给下面的input赋值
            $.post("${pageContext.request.contextPath}/user/queryUser",function(res){
                $("#id").prop("value",res.id);
                $("#username").prop("value",res.username);
                $("#realname").prop("value",res.realName);
                $("#phone").prop("value",res.phone);
                $("#sex").prop("value",res.sex);
                $("#sign").prop("value",res.sign);
                $("#regDate").prop("value",res.registDate);
            },"JSON");
            $("#registBtn").click(function(){
                var id = $("#id").val();
                var username = $("#username").val();
                var realname = $("#realname").val();
                var phone = $("#phone").val();
                var sex = $("#sex").val();
                var sign = $("#sign").val();
                var regDate = $("#regDate").val();
                $.post("${pageContext.request.contextPath}/user/changePersonalMessage","id="+id+"&username="+username+"&realName="+realname+"&phone="+phone+"&sex="+sex+"&sign="+sign+"&registDate="+regDate,function(res){
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
                        $('body').toast({
                            position:'fixed',
                            content:"修改信息成功",
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
        function clickme(){
            if($("#searchByLucene").val() != null && $("#searchByLucene").val() != ""){
                location.href="${pageContext.request.contextPath}/html/commodity2.jsp?page=1&pageRows=9&text="+$("#searchByLucene").val();
            }
        }
    </script>
</head>
<body>

<%@include file="common/head.jsp" %>

<div class="content content-nav-base  login-content">
    <%@include file="common/middle.jsp" %>
    <div class="login-bg">
        <div class="login-cont2 w1200">
            <div class="form-box2">
                <form id="registForm" class="layui-form" action="javascript:void(0)">
                    <legend>个人信息</legend>

                    <div class="layui-form-item">
                        <input type="text" name="id" id="id" hidden="hidden">
                        <div class="layui-inline iphone">
                            用户名：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="username" id="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            真实姓名：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="realname" id="realname" lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            手机号：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="phone" id="phone" lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            性别：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="sex" id="sex" lay-verify="required" placeholder="请输入性别" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            个性签名：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="sign" id="sign" lay-verify="required" placeholder="请输入个性签名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline iphone">
                            注册日期：
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="text" name="regDate" id="regDate" lay-verify="required" placeholder="请输入注册日期" autocomplete="off" class="layui-input">
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