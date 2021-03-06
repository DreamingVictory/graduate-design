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

    <script type="text/javascript">
        var pageNo = 1;
        $(function () {
            layui.config({
                base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
            }).use(['mm', 'carousel'], function () {
                var carousel = layui.carousel,
                    mm = layui.mm;
                var option = {
                    elem: '#test1'
                    , width: '100%' //设置容器宽度
                    , arrow: 'always'
                    , height: '298'
                    , indicator: 'none'
                }
                carousel.render(option);
                // 模版引擎导入
                // var ins = carousel.render(option);
                // var html = demo.innerHTML;
                // var listCont = document.getElementById('list-cont');
                // // console.log(layui.router("#/about.html"))
                //  mm.request({
                //    url: '../json/index.json',
                //    success : function(res){
                //      console.log(res)
                //      listCont.innerHTML = mm.renderHtml(html,res)
                //      ins.relo13[';[/////////////ad(option);
                //    },
                //    error: function(res){
                //      console.log(res);
                //    }
                //  })

                /*轮播图*/
                /*function $(idVlaue){
                    return document.getElementById(idValue);
                }
                var id=window.setInterval(fun,1000);
                var num=1;
                function fun(){
                    if(num==2){num=0;}
                    num++;
                    $("#banner").src="banner"+num+".jpg";
                }
                window.onload=function(){
                    $("#banner").onmouseover=function () {
                        window.clearInterval(id);
                    }
                    $("#banner").onmouseout=function(){
                        id=window.setInterval(fun,1000);
                    }
                }*/
                $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
                    $("#loginUser").text(res);
                },"JSON");

                /*查询所有的分类*/
                $.post(
                    "${pageContext.request.contextPath}/category/queryAllCategory",
                    function (res) {
                        for (var i = 0; i < res.length; i++) {
                            var li = $("<li class=\"nav-item\"></li>");
                            if (res[i].parentId == null) {
                                var div = $("<div class='title'><a href='${pageContext.request.contextPath}/html/commodity.jsp?pageNo=1&pageRows=9&id="+res[i].id+"'>" + res[i].title + "</a></div>");
                                var p = $("<p></p>");
                                for (var j = 0; j < res[i].children.length; j++) {
                                    var a = $("<a href='${pageContext.request.contextPath}/html/commodity.jsp?pageNo=1&pageRows=9&id="+res[i].children[j].id+"'>" + res[i].children[j].title + "</a>");
                                    var ss = $("<i class='layui-icon layui-icon-right'></i>");
                                    p.append(a).append(ss);
                                }
                                li.append(div).append(p);
                            }
                            $("#categoryUl").append(li);
                        }
                    }
                );
                /*更多推荐*/
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

                /*猫狗大作战*/
                $.post(
                    "${pageContext.request.contextPath}/animal/queryByCatDog",
                    function (res) {
                        for (var i = 0; i < res.length; i++) {
                            var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                            $("#catDogBox").append(a);
                        }
                    }
                );
                /*水陆两栖生*/
                $.post(
                    "${pageContext.request.contextPath}/animal/queryByWaterLand",
                    function(res){
                        for(var i=0;i<res.length;i++){
                            var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                            $("#waterLandBox").append(a);
                        }
                    }

                );
                /*花鸟鱼虫*/
                $.post(
                    "${pageContext.request.contextPath}/animal/queryByBirdFish",
                    function(res){
                        for(var i=0;i<res.length;i++){
                            var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                            $("#birdFishBox").append(a);
                        }
                    }

                );

                for (var p = 0; p < 3; p++) {
                    /*今日必抢*/
                    $.ajax({
                        type:"POST",
                        url: "${pageContext.request.contextPath}/animal/queryByDiscount",
                        data: "pageNo=" + pageNo + "&pageRows=4",
                        dataType: "JSON",
                        async: false,
                        success: function (res) {
                            for (var i = 0; i < res.length; i++) {
                                var div = $("<div class=\"item\"></div>");
                                var a = $("<a href='${pageContext.request.contextPath}/animal/queryOneById?id="+res[i].id+"'><img src='http://192.168.46.138/" + res[i].img + "'/></a>");
                                var div2 = $("<div class=\"title\"></div>").text(res[i].title);
                                var div3 = $("<div class=\"price\"></div>");
                                var span = $("<span></span>").text("￥" + res[i].price);
                                var del = $("<del></del>").text("￥" + res[i].cost);
                                div3.append(span).append(del);
                                div.append(a).append(div2).append(div3);
                                $("#lunbo" + pageNo).append(div);

                            }
                        }
                    })
                    pageNo++;
                }
            });
        })

        function clickme(){
            if($("#searchByLucene").val() != null && $("#searchByLucene").val() != ""){
                location.href="${pageContext.request.contextPath}/html/commodity2.jsp?page=1&pageRows=9&text="+$("#searchByLucene").val();
            }
        }

    </script>


</head>
<body id="list-cont">

<%@include file="common/head.jsp"%>

<div class="content">
    <%@include file="common/middle.jsp"%>
    <div class="category-con">
        <div class="category-inner-con w1200">
            <div class="category-type">
                <h3>全部分类</h3>
            </div>
            <div class="category-tab-content">
                <div class="nav-con">
                    <ul class="normal-nav layui-clear" id="categoryUl">

                    </ul>
                </div>
            </div>
        </div>
        <div class="category-banner">
            <div class="w1200">
                <img src="../res/static/img/banner1.jpg" id="banner"/>
            </div>
        </div>
    </div>
    <div class="floors">
        <div class="sk">
            <div class="sk_inner w1200">
                <div class="sk_hd">
                    <a href="javascript:;">
                        <img src="../res/static/img/s_img1.jpg">
                    </a>
                </div>
                <div class="sk_bd">
                    <div class="layui-carousel" id="test1" lay-arrow="always">
                        <div carousel-item>
                            <div class="item-box" id="lunbo1">

                            </div>
                            <div class="item-box" id="lunbo2">

                            </div>
                            <div class="item-box" id="lunbo3">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="hot-recommend-con">
        <div class="tlinks">Collect from <a href="http://www.cssmoban.com/">网页模板</a></div>
        <div class="hot-con1 w1200 layui-clear">
            <div class="item">
                <h4>热销推荐</h4>
                <div class="big-img">
                    <a href="javascript:;"><img src="../res/static/img/hot1.png"></a>
                </div>
                <div class="small-img">
                    <a href="javascript:;"><img src="../res/static/img/hot2.png" alt=""></a>
                </div>
            </div>
            <div class="item">
                <div class="top-img">
                    <a href="javascript:;"><img src="../res/static/img/hot5.jpg"></a>
                </div>
                <div class="bottom-img">
                    <a href="javascript:;"><img src="../res/static/img/hot6.jpg"></a>
                    <a class="baby-cream" href="javascript:;"><img src="../res/static/img/hot7.jpg"></a>
                </div>
            </div>
            <div class="item item1 margin0 padding0">
                <a href="javascript:;"><img src="../res/static/img/hot8.jpg"></a>
                <a href="javascript:;"><img class="btm-img" src="../res/static/img/hot9.jpg"></a>
            </div>
        </div>
    </div>


    <div class="product-cont w1200" id="product-cont">
        <div class="product-item product-item1 layui-clear">
            <div class="left-title">
                <h4><i>1F</i></h4>
                <img src="../res/static/img/icon_gou.png">
                <h5>猫狗大作战</h5>
            </div>
            <div class="right-cont">
                <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
                <div class="img-box" id="catDogBox">

                </div>
            </div>
        </div>
        <div class="product-item product-item2 layui-clear">
            <div class="left-title">
                <h4><i>2F</i></h4>
                <img src="../res/static/img/icon_gou.png">
                <h5>两栖水陆生</h5>
            </div>
            <div class="right-cont">
                <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
                <div class="img-box" id="waterLandBox">

                </div>
            </div>
        </div>
        <div class="product-item product-item3 layui-clear">
            <div class="left-title">
                <h4><i>3F</i></h4>
                <img src="../res/static/img/icon_gou.png">
                <h5>花鸟鱼虫</h5>
            </div>
            <div class="right-cont">
                <a href="javascript:;" class="top-img"><img src="../res/static/img/img12.jpg" alt=""></a>
                <div class="img-box" id="birdFishBox">

                </div>
            </div>
        </div>
    </div>

    <div class="product-list-box" id="product-list-box">
        <div class="product-list-cont w1200">
            <h4>更多推荐</h4>
            <div class="product-item-box layui-clear" id="recommend">

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
        <p class="coty">宠物商城版权所有 &copy; 2012-2020 More Templates <a href="http://www.cssmoban.com/" target="_blank"
                                                                    title="宠物之家">宠物之家</a> - Collect from <a
                href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
    </div>
</div>

</body>
</html>