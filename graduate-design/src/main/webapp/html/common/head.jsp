<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-nav-bg">
    <div class="site-nav w1200">
        <p class="sn-back-home">
            <i class="layui-icon layui-icon-home"></i>
            <a href="index.jsp">首页</a>
        </p>
        <div class="sn-quick-menu">
            <shiro:authenticated>
                欢迎您:<shiro:principal></shiro:principal>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/logoutUser">登出</a>
            </shiro:authenticated>
            <shiro:notAuthenticated>
                <div class="login"><a href="login.jsp">登录</a></div>
                <div class="login"><a href="regist.jsp">注册</a></div>
            </shiro:notAuthenticated>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="sp-cart"><a href="${pageContext.request.contextPath}/cart/getCart">购物车</a></div>
        </div>
    </div>
</div>

<div class="header">
    <div class="headerLayout w1200">
        <div class="headerCon">
            <h1 class="mallLogo">
                <a href="#" title="宠物商城">
                    <img src="../res/static/img/logo.png">
                </a>
            </h1>
            <div class="mallSearch">
                <form action="" class="layui-form" novalidate>
                    <input type="text" id="searchByLucene" name="title" required lay-verify="required"
                           autocomplete="off"
                           class="layui-input" placeholder="请输入需要的商品">
                    <div class="layui-btn" lay-submit lay-filter="formDemo" onclick="clickme()">
                        <i class="layui-icon layui-icon-search"></i>
                    </div>
                    <input type="hidden" name="" value="">
                </form>
            </div>
        </div>
    </div>
</div>
