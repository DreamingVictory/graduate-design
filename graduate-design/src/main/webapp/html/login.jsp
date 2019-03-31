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
    <script src="${pageContext.request.contextPath}/res/js/toast.js"></script>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">


    <script type="text/javascript">


        $(function () {
            layui.config({
                base: '../res/static/js/util' //你存放新模块的目录，注意，不是layui的模块目录
            }).use(['jquery', 'form'], function () {
                var $ = layui.$, form = layui.form;

                $("#captchaImage").click(function () {//点击更换验证码
                    $("#captchaImage").prop("src", "${pageContext.request.contextPath}/vcode/validateImage?time" + new Date());
                });


                $("#loginSuccess").click(
                    function () {
                        $("#loginForm").form("submit",
                            {
                                url: "${pageContext.request.contextPath}/user/login",
                                onSubmit: function () {
                                    return $("#loginForm").form("validate");
                                },
                                success: function (res) {
                                    if (res == "\"ok\"") {
                                        location.href = "${pageContext.request.contextPath}/html/index.jsp";
                                        $.messager.show({
                                            title: "系统提示",
                                            msg: "登录成功"
                                        });
                                    } else {
                                        $('body').toast({
                                            position:'fixed',
                                            content:res,
                                            duration:3000,
                                            isCenter:false,
                                            background:'rgba(230,0,0,0.5)',
                                            animateIn:'bounceIn-hastrans',
                                            animateOut:'bounceOut-hastrans',
                                        });
                                    }
                                }
                            });
                    }
                );
            })
        })

    </script>
</head>
<body>

<%@include file="common/head.jsp"%>

<div class="content content-nav-base  login-content">
    <%@include file="common/middle.jsp"%>
    <div class="login-bg">
        <div class="login-cont w1200">
            <div class="form-box">
                <form id="loginForm" class="layui-form" method="post">
                    <legend>手机号登录</legend>
                    <div class="layui-form-item">
                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="tel" name="phone" id="phone" lay-verify="required|phone"
                                       placeholder="请输入手机号" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline iphone">
                            <div class="layui-input-inline">
                                <i class="layui-icon layui-icon-cellphone iphone-icon"></i>
                                <input type="password" name="password" id="password" lay-verify="required"
                                       placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline veri-code">
                            <div class="layui-input-inline">
                                <input type="text" name="vcode" lay-verify="required" placeholder="请输入验证码"
                                       autocomplete="off" class="layui-input" style="width: 150px"><img
                                    style="width: 150px" id="captchaImage" class="layui-btn" class="captchaImage"
                                    src="${pageContext.request.contextPath}/vcode/validateImage" title="点击更换验证码"/>
                            </div>
                        </div>

                    </div>
                    <div class="layui-form-item login-btn">
                        <div class="layui-input-block">
                            <input style="width: 300px;" type="button" id="loginSuccess" class="layui-btn" lay-filter="demo1" value="登录"></input>
                            <span id="loginerror"></span>
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
            <a href="javascript:;">母婴资讯</a>
            <span>|</span>
            <a href="javascript:;">关于货源</a>
        </p>
        <p class="coty">母婴商城版权所有 &copy; 2012-2020</p>
    </div>
</div>


</body>
</html>