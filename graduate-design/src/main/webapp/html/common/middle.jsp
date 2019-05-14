<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="main-nav">
    <div class="inner-cont0">
        <div class="inner-cont1 w1200">
            <div class="inner-cont2">
                <a href="${pageContext.request.contextPath}/html/commodity.jsp?pageNo=1&pageRows=9 " class="active">所有商品</a>
                <a href="${pageContext.request.contextPath}/user/findByPhone?phone=<shiro:principal></shiro:principal>">个人信息</a>
                <a href="information.html">宠物资讯</a>
                <a href="about.html">关于我们</a>
            </div>
        </div>
    </div>
</div>