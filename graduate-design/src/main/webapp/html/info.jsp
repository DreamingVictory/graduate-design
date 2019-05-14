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
  <script type="text/javascript" src="../res/js/jquery.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

  <script>
      $.ajax({
          type: "POST",
          url: "${pageContext.request.contextPath}/animal/queryBySale",
          dataType: "JSON",
          success:function (res) {
              for (var i = 0; i < res.length; i++) {
                  var div="<div class=\"item\">\n" +
                      "              <img src=http://192.168.46.138\+res[i].img+ alt=\"\">\n" +
                      "              <div class=\"text\">\n" +
                      "                <div class=\"right-title-number\">"+Number(i)+1+"</div>\n" +
                      "                <div class=\"commod\">\n" +
                      "                  <p>"+res[i].title+"</p>\n" +
                      "                  <span>￥"+res[i].ciurPic+"</span>\n" +
                      "                </div>\n" +
                      "              </div>\n" +
                      "            </div>";
                  $("#footerList").append(div);
              }
          }
      });

      $.ajax({
          type:"POST",
          url:"${pageContext.request.contextPath}/user/update",
          data:"user"=
      });
  </script>
</head>
<body>
<%@include file="common/head.jsp"%>
  <div class="content content-nav-base buytoday-content">
    <div id="list-cont">
      <div class="main-nav">
        <%@include file="common/middle.jsp"%>
      </div>
      <div class="banner-box">
        <div class="banner"></div>
      </div>
      <div class="product-list-box">
        <div class="product-list w1200">  
          <div class="tab-title">
            <a href="javascript:;" data-content="yukao">个人信息</a>
          </div>
          <div class="list-cont" cont = 'tuangou'>
            <div class="item-box layui-clear">
              <table id="info" align="center" style="font-size: 20px;align-content: center">
                <tr>
                  <td><input type="text" value="姓名"></td>
                  <td><input type="text" value="${sessionScope.detailUser.username}"></td>
                </tr>
                <tr>
                  <td><input type="text" value="电话"></td>
                  <td><input type="text" value="${sessionScope.detailUser.phone}" readonly></td>
                </tr>
                <tr>
                  <td><input type="text" value="性别"></td>
                  <td><input type="text" value="${sessionScope.detailUser.sex}"></td>
                </tr>
                <tr>
                  <td><input type="text" value="真实姓名"></td>
                  <td><input type="text" value="${sessionScope.detailUser.realName}" readonly></td>
                </tr>
                <tr>
                  <td><input type="text" value="注册日期"></td>
                  <td><input type="text" value="${sessionScope.detailUser.registDate}" readonly></td>
                </tr>
                <tr>
                  <td><input type="text" value="用户状态"></td>
                  <td><input type="text" value="${sessionScope.detailUser.status}" readonly></td>
                </tr>
                <tr>
                  <td><input type="text" value="省份"></td>
                  <td><input type="text" value="${sessionScope.detailUser.province}"></td>
                </tr>
                <tr>
                  <td><input type="text" value="签名"></td>
                  <td><input type="text" value="${sessionScope.detailUser.sign}"></td>
                </tr>
                <tr>
                  <td colspan="2"><a href=""><button style="align-content: center" id="submitUpdate">提交修改</button></a></td>
                </tr>

              </table>
            </div>
            <div id="demo0" style="text-align: center;"></div>
          </div>
        </div>  
      </div>
      <div class="footer-wrap">
        <div class="footer w1200">
          <div class="title">
            <h3>销量榜单</h3>
          </div>
          <div class="list-box layui-clear" id="footerList">
            <%--<div class="item">
              <img src=http://192.168.46.138/${animal.animal.img} alt="">
              <div class="text">
                <div class="right-title-number">1</div>
                <div class="commod">
                  <p>宝宝打底衣棉麻透气不起球白色多色可选</p>
                  <span>￥100.00</span>
                </div>
              </div>
            </div>

            --%>
          </div>
        </div>
      </div>
    </div>
  </div>


<script>

  layui.config({
    base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
  }).use(['mm','laypage','jquery'],function(){
     /* var laypage = layui.laypage,$ = layui.$;
     mm = layui.mm;
      laypage.render({
        elem: 'demo0'
        ,count: 100 //数据总数
      });*/


      $('body').on('click','*[data-content]',function(){
        $(this).addClass('active').siblings().removeClass('active')
        var dataConte = $(this).attr('data-content');
        $('*[cont]').each(function(i,item){
          var itemCont = $(item).attr('cont');
          console.log(item)
          if(dataConte === itemCont){
            $(item).removeClass('layui-hide');
          }else{
            $(item).addClass('layui-hide');
          }
        })
      })


});
</script>


</body>
</html>