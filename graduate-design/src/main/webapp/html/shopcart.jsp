<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
  <script type="text/javascript" src="../res/layui/layui.js"></script>
  <script src="../res/js/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <script>

    function del(id) {
        $.post(
            "${pageContext.request.contextPath}/cart/deleteCart","id="+id,function(){},"JSON"
        );
    }

  </script>

</head>
<body>
  <%@include file="common/head.jsp"%>
  <div class="content content-nav-base shopcart-content">
    <%@include file="common/middle.jsp"%>
    <div class="banner-bg w1200">
      <h3>萌宠会馆</h3>
      <p>爱宠之家</p>
    </div>
    <div class="cart w1200">
      <div class="cart-table-th">
        <div class="th th-chk">
          <div class="select-all">
            <div class="cart-checkbox">
              <input class="check-all check" id="allCheckked" type="checkbox" value="true">
            </div>
          <label>&nbsp;&nbsp;全选</label>
          </div>
        </div>
        <div class="th th-item">
          <div class="th-inner">
            商品
          </div>
        </div>
        <div class="th th-price">
          <div class="th-inner">
            单价
          </div>
        </div>
        <div class="th th-amount">
          <div class="th-inner">
            数量
          </div>
        </div>
        <div class="th th-sum">
          <div class="th-inner">
            小计
          </div>
        </div>
        <div class="th th-op">
          <div class="th-inner">
            操作
          </div>
        </div>  
      </div>

      <div class="OrderList">
        <div class="order-content" id="list-cont">
          <c:forEach items="${sessionScope.cartitem}" var="animal">
          <ul class="item-content layui-clear">
            <li class="th th-chk">
              <div class="select-all">
                <div class="cart-checkbox">
                  <input class="CheckBoxShop check"  type="checkbox" num="all" name="select-all" value="true">
                </div>
              </div>
            </li>
            <li class="th th-item">
              <div class="item-cont">
                <a href="javascript:;"><img width="20px" height="20px" style="margin: 0px 0px 20px 50px" src=http://192.168.46.138/${animal.animal.img} /></a>
                <div class="text">
                  <div class="title">${animal.animal.title}</div>
                </div>
              </div>
            </li>
            <li class="th th-price">
              <span class="th-su">${animal.animal.price}</span>
            </li>
            <li class="th th-amount">
              <div class="box-btn layui-clear">
                <div class="less layui-btn">-</div>
                <input class="Quantity-input" type="" name="" value="${animal.count}" disabled="disabled">
                <div class="add layui-btn">+</div>
              </div>
            </li>
            <li class="th th-sum">
              <span class="sum">${animal.totalPrice}</span>
            </li>
            <li class="th th-op">
              <a class="dele-btn" id="delete_cartitem" onclick="del(${animal.animal.id})">删除</a></span>
            </li>
          </ul>
          </c:forEach>
        </div>
      </div>

      <div class="FloatBarHolder layui-clear">
        <div class="th th-chk">
          <div class="select-all">
            <div class="cart-checkbox">
              <input class="check-all check" id="" name="select-all" type="checkbox"  value="true">
            </div>
            <label>&nbsp;&nbsp;已选<span class="Selected-pieces">0</span>件</label>
          </div>
        </div>
        <div class="th batch-deletion">
          <span class="batch-dele-btn">批量删除</span>
        </div>
        <div class="th Settlement">
          <button class="layui-btn"><a href="${pageContext.request.contextPath}/html/order.jsp">结算</a></button>
        </div>
        <div class="th total">
          <p>应付：<span class="pieces-total">0</span></p>
        </div>
      </div>
    </div>
  </div>

<script type="text/javascript">
  layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','jquery','element','car'],function(){
    var mm = layui.mm,$ = layui.$,element = layui.element,car = layui.car;
    car.init()


});
</script>
</body>
</html>