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

    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

    <script>
        var address;
        var aaa = 0;
        $(function () {
            $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
                $("#loginUser").text(res);
            },"JSON");
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/address/queryHistoryAddress",
                dataType: "JSON",
                success: function (res) {
                    address = res;
                    $("#history").empty();
                    for (var i = 0; i < res.length; i++) {
                        var option1 = "<option value='"+res[i].id+"'>" + res[i].detailAddress + "</option>";
                        $("#history").append(option1);
                    }
                    var span = $("<span>收件人姓名："+res[0].reciveName+"</span><br>" +
                        "                                <span>收件人电话："+res[0].telphone+"</span><br>" +
                        "                                <span>详细地址："+res[0].detailAddress+"</span><br>");
                    $("#showAddress").append(span);
                    $("#reciveName").prop("value",res[0].reciveName);
                    $("#province").prop("value",res[0].province);
                    $("#city").prop("value",res[0].city);
                    $("#telphone").prop("value",res[0].telphone);
                    $("#detailAddress").prop("value",res[0].detailAddress);
                }
            });

            $("#history").click(function(){
                var id = $("#history").val();
                for(var i=0; i<address.length; i++){
                    if(id == address[i].id){
                        $("#showAddress").empty();
                        var span = $("<span>收件人姓名："+address[i].reciveName+"</span><br>" +
                            "                                <span>收件人电话："+address[i].telphone+"</span><br>" +
                            "                                <span>详细地址："+address[i].detailAddress+"</span><br>");
                        $("#showAddress").append(span);
                        $("#reciveName").prop("value",address[i].reciveName);
                        $("#province").prop("value",address[i].province);
                        $("#city").prop("value",address[i].city);
                        $("#telphone").prop("value",address[i].telphone);
                        $("#detailAddress").prop("value",address[i].detailAddress);
                    }
                }
            });

            $("#resetAddress").click(function(){
                $("#reciveName").prop("value","");
                $("#province").prop("value","");
                $("#city").prop("value","");
                $("#telphone").prop("value","");
                $("#detailAddress").prop("value","");
                aaa += 1;
            });

            $("#submitAddress").click(function(){

                if(aaa != 0){
                    var reciveName = $("#reciveName").val();
                    var province = $("#province").val();
                    var city = $("#city").val();
                    var telphone = $("#telphone").val();
                    var detailAddress = $("#detailAddress").val();
                    $.post("${pageContext.request.contextPath}/address/addAddress","reciveName="+reciveName+"&province="+province+"&city="+city+"&telphone="+telphone+"&detailAddress="+detailAddress,function(r){
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/address/queryHistoryAddress",
                            dataType: "JSON",
                            success: function (res) {
                                address = res;
                                var t = null;
                                $("#history").empty();
                                for (var i = 0; i < res.length; i++) {
                                    var option1;
                                    if(r == res[i].id){
                                        t = i;
                                        option1 = "<option value='"+res[i].id+"' selected='selected'>" + res[i].detailAddress + "</option>";
                                    }else{
                                        option1 = "<option value='"+res[i].id+"'>" + res[i].detailAddress + "</option>";
                                    }
                                    $("#history").append(option1);
                                }
                                $("#showAddress").empty();
                                var span = $("<span>收件人姓名："+res[t].reciveName+"</span><br>" +
                                    "                                <span>收件人电话："+res[t].telphone+"</span><br>" +
                                    "                                <span>详细地址："+res[t].detailAddress+"</span><br>");
                                $("#showAddress").append(span);
                            }
                        });
                    },"JSON");
                }
                aaa = 0;
            });
        })

        function submit() {
            location.href="${pageContext.request.contextPath}/html/orderSuccess.jsp?addressId="+$("#history").val();
        }
        function clickme(){
            if($("#searchByLucene").val() != null && $("#searchByLucene").val() != ""){
                location.href="${pageContext.request.contextPath}/html/commodity2.jsp?page=1&pageRows=9&text="+$("#searchByLucene").val();
            }
        }
    </script>
</head>
<body>
<%@include file="common/head.jsp" %>
<div class="content content-nav-base shopcart-content">
    <%@include file="common/middle.jsp" %>
    <div class="content content-nav-base information-content">

        <div class="info-list-box">
            <div class="info-list w1200">
                <div class="item-box layui-clear" id="list-cont">
                    <div class="item">
                        <div class="img">
                            <img src="../res/static/img/new1.jpg" alt="">
                        </div>
                        <div class="text">
                            <h4>历史地址</h4>
                            <select class="data" id="history"></select>
                            <p class="info-cont" id="showAddress">
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <div class="img">
                            <img src="../res/static/img/new1.jpg" alt="">
                        </div>
                        <div class="text">

                            收件人：<input class="info-cont" id="reciveName" type="text">
                            收件人省份：<input class="info-cont" id="province" type="text"/>
                            收件人城市：<input class="info-cont" id="city" type="text"/>
                            收件人手机：<input class="info-cont" id="telphone" type="text"/>
                            收件人详细地址：<input class="info-cont" id="detailAddress" type="text"/><br/>
                            <p class="info-cont" id="submitAddress">提交新地址</p>
                            <p class="info-cont" id="resetAddress">重置</p>
                        </div>
                    </div>
                </div>
                <div id="demo0" style="text-align: center;"></div>
            </div>
        </div>
    </div>
    <div class="cart w1200">
        <div class="cart-table-th">
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

        </div>

        <div class="OrderList">
            <div class="order-content">
                <c:forEach items="${sessionScope.cartitem}" var="animal">
                    <ul class="item-content layui-clear">
                        <li class="th th-item">
                            <div class="item-cont">
                                <a href="javascript:;"><img width="20px" height="20px" style="margin: 0px 0px 20px 50px"
                                                            src=http://192.168.46.138/${animal.animal.img} /></a>
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
                                <input class="Quantity-input" type="" name="" value="${animal.count}" disabled="disabled">
                            </div>
                        </li>
                        <li class="th th-sum">
                            <span class="sum">${animal.totalPrice}</span>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>

        <div class="FloatBarHolder layui-clear">
            <div class="th total">
                <p>应付：<span class="pieces-total">${sessionScope.totalPrice}</span></p>
            </div>
            <div class="th Settlement">
                <button class="layui-btn" onclick="submit()">提交订单</button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    layui.config({
        base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['mm', 'jquery', 'element', 'car'], function () {
        var mm = layui.mm, $ = layui.$, element = layui.element, car = layui.car;
        car.init();


    });
</script>
</body>
</html>