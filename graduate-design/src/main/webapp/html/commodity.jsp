<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <script type="text/javascript" src="../res/layui/layui.js"></script>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

    <script>

        layui.config({
            base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
        }).use(['mm', 'laypage', 'jquery'], function () {
            var laypage = layui.laypage, $ = layui.$,
                mm = layui.mm;
            laypage.render({
                elem: 'demo0'
                , count: 100 //数据总数
            });


            // 模版引擎导入
            //  var html = demo.innerHTML;
            //  var listCont = document.getElementById('list-cont');
            //  // console.log(layui.router("#/about.html"))
            // mm.request({
            //     url: '../json/commodity.json',
            //     success : function(res){
            //       console.log(res)
            //       listCont.innerHTML = mm.renderHtml(html,res)
            //     },
            //     error: function(res){
            //       console.log(res);
            //     }
            //   })




            $(function () {
                $.post(
                    "${pageContext.request.contextPath}/animal/queryAllAnimal",
                    function (res) {

                        for (var i = 0; i < res.length; i++) {
                            var div1 = $("<div class=\"item\"></div>");
                            var div2 = $("<div class=\"img\" style='margin: 50px 100px 50px 50px;'></div>");
                            var a = $("<a href='javascript:;'><img src='" + res[i].img + "'/></a>");
                            div2.append(a);
                            var div3 = $("<div class=\"text\"></div>");
                            var p1 = $("<p class=\"title\" style='margin-left: 10px'>" + res[i].title + "</p>");
                            var p2 = $("<p class=\"price\"></p>");
                            var span1 = $("<span class=\"pri\" style='margin-left: 10px'>" + "￥" + res[i].ciurPic + "</span>");
                            var span2 = $("<span class=\"nub\">" + res[i].count + "付款" + "</span>");
                            p2.append(span1).append(span2);
                            div3.append(p1).append(p2);
                            div1.append(div2).append(div3);
                            $("#list-cont").append(div1);
                        }
                    }
                );

                $.post(
                    "${pageContext.request.contextPath}/animal/totalCount",
                    function (res) {
                        $("#count").html("一共帮您找到了" + "<b>" + res + "</b>" + "个小动物");
                    }
                );

                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/category/queryAllCategory",
                    dataType:"JSON",
                    async:false,
                    success:function (res) {
                        for (var i = 0; i < res.length; i++) {
                            var dl = $("<dl></dl>");
                            var dt = $("<dt>" + res[i].title + "</dt>");
                            dl.append(dt);
                            for (var j = 0; j < res[i].children.length; j++) {
                                var dd = $("<dd><a href=\"javascript:;\">" + res[i].children[j].title + "</a></dd>");
                                dl.append(dd);
                            }
                            $("#categoryAll").append(dl);
                        }

                    }
                });

                $('.sort a').on('click', function () {
                    $(this).addClass('active').siblings().removeClass('active');
                })
                $('.list-box dt').on('click', function () {
                    if ($(this).attr('off')) {
                        $(this).removeClass('active').siblings('dd').show()
                        $(this).attr('off', '')
                    } else {
                        $(this).addClass('active').siblings('dd').hide()
                        $(this).attr('off', true)
                    }
                })

            })

        });
    </script>
</head>
<body>

<div class="site-nav-bg">
    <div class="site-nav w1200">
        <p class="sn-back-home">
            <i class="layui-icon layui-icon-home"></i>
            <a href="#">首页</a>
        </p>
        <div class="sn-quick-menu">
            <div class="login"><a href="login.html">登录</a></div>
            <div class="sp-cart"><a href="shopcart.html">购物车</a><span>2</span></div>
        </div>
    </div>
</div>


<div class="header">
    <div class="headerLayout w1200">
        <div class="headerCon">
            <h1 class="mallLogo">
                <a href="#" title="母婴商城">
                    <img src="../res/static/img/logo.png">
                </a>
            </h1>
            <div class="mallSearch">
                <form action="" class="layui-form" novalidate>
                    <input type="text" name="title" required lay-verify="required" autocomplete="off"
                           class="layui-input" placeholder="请输入需要的商品">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                    <input type="hidden" name="" value="">
                </form>
            </div>
        </div>
    </div>
</div>


<div class="content content-nav-base commodity-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <div class="inner-cont2">
                    <a href="commodity.html" class="active">所有商品</a>
                    <a href="buytoday.html">今日团购</a>
                    <a href="information.html">母婴资讯</a>
                    <a href="about.html">关于我们</a>
                </div>
            </div>
        </div>
    </div>
    <div class="commod-cont-wrap">
        <div class="commod-cont w1200 layui-clear">
            <div class="left-nav">
                <div class="title">所有分类</div>
                <div class="list-box" id="categoryAll">

                </div>
            </div>
            <div class="right-cont-wrap">
                <div class="right-cont">
                    <div class="sort layui-clear">
                        <a class="active" href="javascript:;" event='volume'>销量</a>
                        <a href="javascript:;" event='price'>价格</a>
                        <a href="javascript:;" event='newprod'>新品</a>
                        <a href="javascript:;" event='collection'>收藏</a>
                    </div>
                    <div class="prod-number">
                        <span id="count" style="color: #9175ad;font-family: '微软雅黑', 'Microsoft Yahei'"></span>
                    </div>
                    <div class="cont-list layui-clear" id="list-cont">
                        <%--  <c:forEach items="${sessionScope.list}" var="animal">
                            <div class="item">
                              <div class="img">
                                <a href="javascript:;"><img src=${animal.img}></a>
                              </div>
                              <div class="text">
                                <p class="title">${animal.title}</p>
                                <p class="price">
                                  <span class="pri">￥${animal.ciurPic}</span>
                                  <span class="nub">${animal.count}付款</span>
                                </p>
                              </div>
                            </div>
                          </c:forEach>--%>

                    </div>
                    <!-- 模版引擎导入 -->
                    <!-- <script type="text/html" id="demo">
                      {{# layui.each(d.menu.milk.content,function(index,item){}}
                        <div class="item">
                          <div class="img">
                            <a href="javascript:;"><img src="{{item.img}}"></a>
                          </div>
                          <div class="text">
                            <p class="title"></p>
                            <p class="price">
                              <span class="pri">{{item.pri}}</span>
                              <span class="nub">{{item.nub}}</span>
                            </p>
                          </div>
                        </div>
                      {{# }); }}
                    </script> -->
                    <div id="demo0" style="text-align: center;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>