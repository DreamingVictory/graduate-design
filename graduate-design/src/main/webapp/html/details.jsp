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
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

  <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>

  <script type="text/javascript">
      layui.config({
          base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
      }).use(['mm','jquery'],function(){
          var mm = layui.mm,$ = layui.$;
          var cur = $('.number-cont input').val();
          $('.number-cont .btn').on('click',function(){
              if($(this).hasClass('add')){
                  cur++;

              }else{
                  if(cur > 1){
                      cur--;
                  }
              }
              $('.number-cont input').val(cur)
          })

      });

      $(function(){
          $(".image").mouseover(function(e){
              var bigImg=$("<img id='bimg' src='"+$(this).attr("src")+"'/>");
              $("#bimg").css({
                  "height":350,
                  "top":e.pageY+10,
                  "left":e.pageX+15,
                  "position":"absolute"
              });
              $("body").append(bigImg);
          }).mouseout(function(){
              $("#bimg").remove();
          }).mousemove(function(e){
              $("#bimg").css({
                  "height":350,
                  "top":e.pageY+10,
                  "left":e.pageX+15,
                  "position":"absolute"
              })
          });
          //猜你喜欢
          $.post(
              "${pageContext.request.contextPath}/animal/queryAnimalsByCommend",
              function (res) {
                  for (var i = 0; i < res.length; i++) {
                      var div = $("<div class=\"list-item\"></div>");
                      var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                      var p = $("<p style=\"margin-left: 50px\"></p>").text(res[i].title);
                      var span = $("<span></span>").text("￥" + res[i].price);
                      var span2 = $("<del style='padding-left: 40px'></del>").text("￥" + res[i].cost);
                      div.append(a).append(p).append(span).append(span2);
                      $("#recommend").append(div);
                  }
              }
          );


          /*热销推荐*/
          $.post(
              "${pageContext.request.contextPath}/animal/queryByCount",
              function (res) {
                for(var i=0;i<res.length;i++){
                    console.log(res[i].ciurPic)
                    var div=$("<div class=\"item\"></div>");
                    var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                    var p=$("<p><span>"+res[i].title+"</span><span style='margin-left: 60px;color: palevioletred' class='pric'>￥"+res[i].ciurPic+"</span></p>");
                    $("#countAnimal").append(div).append(a).append(p);
                }
              }
          );
      });
      function add(id){
        $.post(
            "${pageContext.request.contextPath}/cart/addCart",
            "id="+id+"&count="+$("#addCount").val(),
            function(res){
                var s = "\"ok\"";
                if(res==s){
                    location.href="${pageContext.request.contextPath}/cart/getCart";
                }else{
                    location.href="${pageContext.request.contextPath}/html/login.jsp";
                }
            }
        );
      }
  </script>
</head>
<body>
<%@include file="common/head.jsp"%>
  <div class="content content-nav-base datails-content">
    <%@include file="common/middle.jsp"%>
    <div class="data-cont-wrap w1200">
      <div class="crumb">
        <a href="${pageContext.request.contextPath}/html/index.jsp">首页</a>
        <span>></span>
        <a href="${pageContext.request.contextPath}/html/commodity.jsp">所有商品</a>
        <span>></span>
        <a href="${pageContext.request.contextPath}/html/details.jsp">产品详情</a>
      </div>
      <div class="product-intro layui-clear">
          <div class="preview-wrap">
            <a href="javascript:;"><img class="image" style="width: 300px;height: 300px;margin: 50px auto" src="http://192.168.46.138/${sessionScope.details.img}"></a>
          </div>
          <div class="itemInfo-wrap">
            <div class="itemInfo">
              <div class="title">
                <h4 style="margin-left: 40%" >${sessionScope.details.title} </h4>
                <span><i class="layui-icon layui-icon-rate-solid"></i>收藏</span>
              </div>
              <div class="summary">
                <p class="reference"><span>参考价</span> <del>￥${sessionScope.details.cost}</del></p>
                <p class="activity"><span>活动价</span><strong class="price"><i>￥</i>${sessionScope.details.price}</strong></p>
                <p class="address-box"><span>描&nbsp;&nbsp;&nbsp;&nbsp;述</span><strong class="address">${sessionScope.details.description}</strong></p>
              </div>
              <div class="choose-attrs">
                <div class="number layui-clear"><span class="title">数&nbsp;&nbsp;&nbsp;&nbsp;量</span><div class="number-cont"><span class="cut btn">-</span><input onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="4" type="" name="" value="1" id="addCount"><span class="add btn">+</span></div></div>
              </div>
              <div class="choose-btns">
                <button class="layui-btn layui-btn-primary purchase-btn">立刻购买</button>
                <button class="layui-btn  layui-btn-danger car-btn" onclick=add("${sessionScope.details.id}")><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</button>
              </div>
            </div>
          </div>
      </div>
      <div class="layui-clear">
        <%--<div class="aside">
          <h4>热销推荐</h4>
          <div class="item-list" id="countAnimal">

          </div>
        </div>--%>
        <%--<div class="detail">
          <h4>猜你喜欢</h4>
          <div class="item">

          </div>
        </div>--%>
        <div class="product-list-box" id="product-list-box">
          <div class="product-list-cont w1200">
            <h4>猜你喜欢</h4>
            <div class="product-item-box layui-clear" id="recommend">

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>