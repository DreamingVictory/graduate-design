<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="main-nav">
    <div class="inner-cont0">
        <div class="inner-cont1 w1200">
            <div class="inner-cont2">
                <a href="${pageContext.request.contextPath}/html/commodity.jsp?pageNo=1&pageRows=9 " class="active">所有商品</a>
                <shiro:authenticated>
                    <a href="${pageContext.request.contextPath}/html/changePassword.jsp">修改密码</a>
                    <a href="${pageContext.request.contextPath}/html/personalMsg.jsp">个人信息</a>
                    <a href="${pageContext.request.contextPath}/html/address.jsp">收货地址</a>
                    <a href="${pageContext.request.contextPath}/html/showOrder.jsp">历史订单</a>
                </shiro:authenticated>
                <a href="#">关于我们</a>
            </div>
        </div>
    </div>
</div>