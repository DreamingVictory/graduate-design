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
        function clickme(){
            if($("#searchByLucene").val() != null && $("#searchByLucene").val() != ""){
                location.href="${pageContext.request.contextPath}/html/commodity2.jsp?page=1&pageRows=9&text="+$("#searchByLucene").val();
            }
        }

        $(function(){
            var addressId = "${param.addressId}";
            $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
                $("#loginUser").text(res);
            },"JSON");
            $.post("${pageContext.request.contextPath}/order/insertOrder","addressId="+addressId,function(res){
                $("#showOrder").empty();

                var address;
                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/address/queryAddressById",
                    data:"id="+res.addressId,
                    dataType:"JSON",
                    async:false,
                    success:function(re){
                        address = re;
                    }
                });
                var div = "<div class=\"order-content\" id=\"list-cont\">\n" +
                    "                    <ul class=\"aaaa\">\n" +
                    "                        <li class=\"th2 th-price2\">\n" +
                    "                            <span class=\"th-su\">"+res.orderId+"</span>\n" +
                    "                        </li>\n" +
                    "                        <li class=\"th2 th-price2\">\n" +
                    "                            <span class=\"th-su\">"+res.totalPrice+"</span>\n" +
                    "                        </li>\n" +
                    "                        <li class=\"th2 th-price2\">\n" +
                    "                            <span class=\"th-su\">"+address.reciveName+"</span>\n" +
                    "                        </li>\n" +
                    "                        <li class=\"th2 th-price2\">\n" +
                    "                            <span class=\"th-su\">"+address.detailAddress+"</span>\n" +
                    "                        </li>\n" +
                    "                        <li class=\"th2 th-price2\">\n" +
                    "                            <span class=\"sum\">"+res.createTime+"</span>\n" +
                    "                        </li>\n" +
                    "                    </ul>\n" +
                    "            </div>";
                $("#showOrder").append(div);
            },"JSON");
        });



    </script>

</head>
<body>
<%@include file="common/head.jsp"%>
<div class="content content-nav-base shopcart-content">
    <%@include file="common/middle.jsp"%>
    <p>&nbsp;</p>
    <div class="cart w1200">
        <div class="cart-table-th">
            <div class="th th-price">
                <div class="th-inner">
                    订单编号
                </div>
            </div>
            <div class="th th-price">
                <div class="th-inner">
                    总价
                </div>
            </div>
            <div class="th th-price">
                <div class="th-inner">
                    收货人
                </div>
            </div>
            <div class="th th-amount">
                <div class="th-inner">
                    地址
                </div>
            </div>
            <div class="th th-sum">
                <div class="th-inner">
                    日期
                </div>
            </div>
        </div>

        <div class="OrderList" id="showOrder">

        </div>
        <div class="FloatBarHolder layui-clear">
            <div class="th Settlement">
                <button class="layui-btn" style="height: 60px"><a href="#">支付</a></button>
            </div>
        </div>
    </div>
</div>

</body>
</html>