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
    <script type="text/javascript" src="../res/js/jquery.min.js"></script>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">

    <script>
        var page;
        var id;
        var status;
        layui.config({
            base: '../res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
        }).use(['mm', 'laypage', 'jquery'], function () {
            /*var laypage = layui.laypage, $ = layui.$,
                mm = layui.mm;
            laypage.render({
                elem: 'demo0'
                , count: 100 //数据总数
            });*/
            $(function () {
                id = "${param.id}";
                page = "${param.pageNo}";
                $("#currentPage").text(page);
                status=0;
                $.post("${pageContext.request.contextPath}/user/getUsername",function(res){
                    $("#loginUser").text(res);
                },"JSON");

                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/animal/totalPage",
                    data:"id="+id,
                    success:function (res) {
                        $("#totalPage").text(res);
                    }
                });

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/animal/findAnimalCount",
                    data:"id="+id,
                    dataType: "JSON",
                    success:function (res) {
                        $("#count").text(res);
                    }
                });


                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/category/queryAllCategory",
                    dataType: "JSON",
                    async: false,
                    success: function (res) {
                        for (var i = 0; i < res.length; i++) {
                            var dl = $("<dl></dl>");
                            var dt = $("<dt>" + res[i].title + "</dt>");
                            dl.append(dt);
                            for (var j = 0; j < res[i].children.length; j++) {
                                //var dd = $("<dd><a href='${pageContext.request.contextPath}/animal/queryAnimals?id=" + res[i].children[j].id + "'>" + res[i].children[j].title + "</a></dd>");
                                var dd = $("<dd><a href='javascript:;' id='dd' onclick='secondsCategory("+res[i].children[j].id+")'>" + res[i].children[j].title + "</a></dd>");
                                dl.append(dd);
                            }
                            $("#categoryAll").append(dl);
                        }
                    }
                });

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/animal/animals",
                    data:"id="+id+"&page="+page+"&rows=${param.pageRows}",
                    dataType: "JSON",
                    async: false,
                    success: function (res) {
                        $("#list-cont").empty();
                        for (var i = 0; i < res.length; i++) {
                            var animal = res[i];

                            var div = "<div class=\"item\">\n" +
                                "                                <div class=\"img\">\n" +
                                "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                "                                </div>\n" +
                                "                                <div class=\"text\">\n" +
                                "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                "                                    <p class=\"price\">\n" +
                                "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                "                                    </p>\n" +
                                "                                </div>\n" +
                                "                            </div>";
                            $("#list-cont").append(div);
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
                
                $("#saleCount").click(function () {
                    if(status==0){
                        status=1;
                    }else{
                        status=0;
                    }

                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/animal/orderBySaleCount",
                        data:"id="+id+"&status="+status+"&page="+page+"&rows=${param.pageRows}",
                        dataType: "JSON",
                        success:function (res) {
                            $("#list-cont").empty();
                            for (var i = 0; i < res.length; i++) {
                                var animal = res[i];

                                var div = "<div class=\"item\">\n" +
                                    "                                <div class=\"img\">\n" +
                                    "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                    "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                    "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"text\">\n" +
                                    "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                    "                                    <p class=\"price\">\n" +
                                    "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                    "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                    "                                    </p>\n" +
                                    "                                </div>\n" +
                                    "                            </div>";
                                $("#list-cont").append(div);
                            }
                        }
                    })
                })

                $("#price").click(function () {
                    if(status==0){
                        status=1;
                    }else{
                        status=0;
                    }

                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/animal/orderByPrice",
                        data:"id="+id+"&status="+status+"&page="+page+"&rows=${param.pageRows}",
                        dataType: "JSON",
                        success:function (res) {
                            $("#list-cont").empty();
                            for (var i = 0; i < res.length; i++) {
                                var animal = res[i];
                                var div = "<div class=\"item\">\n" +
                                    "                                <div class=\"img\">\n" +
                                    "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                    "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                    "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"text\">\n" +
                                    "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                    "                                    <p class=\"price\">\n" +
                                    "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                    "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                    "                                    </p>\n" +
                                    "                                </div>\n" +
                                    "                            </div>";
                                $("#list-cont").append(div);
                            }
                        }
                    })
                })
                
                $("#pubDate").click(function () {
                    if(status==0){
                        status=1;
                    }else{
                        status=0;
                    }

                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/animal/orderByDate",
                        data:"id="+id+"&status="+status+"&page="+page+"&rows=${param.pageRows}",
                        dataType: "JSON",
                        success:function (res) {
                            $("#list-cont").empty();
                            for (var i = 0; i < res.length; i++) {
                                var animal = res[i];

                                var div = "<div class=\"item\">\n" +
                                    "                                <div class=\"img\">\n" +
                                    "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                    "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                    "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"text\">\n" +
                                    "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                    "                                    <p class=\"price\">\n" +
                                    "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                    "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                    "                                    </p>\n" +
                                    "                                </div>\n" +
                                    "                            </div>";
                                $("#list-cont").append(div);
                            }
                        }
                    })
                })
                
                $("#lastPage").click(function () {
                    if(page>1){
                        page=Number(page)-1;
                        $("#currentPage").text(page);
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/animal/animals",
                            data:"id="+id+"&page="+page+"&rows=${param.pageRows}",
                            dataType: "JSON",
                            async: false,
                            success: function (res) {
                                $("#list-cont").empty();
                                for (var i = 0; i < res.length; i++) {
                                    var animal = res[i];

                                    var div = "<div class=\"item\">\n" +
                                        "                                <div class=\"img\">\n" +
                                        "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                        "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                        "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"text\">\n" +
                                        "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                        "                                    <p class=\"price\">\n" +
                                        "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                        "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                        "                                    </p>\n" +
                                        "                                </div>\n" +
                                        "                            </div>";
                                    $("#list-cont").append(div);
                                }

                            }
                        });
                    }

                })


                $("#nextPage").click(function () {
                    if(page<$("#totalPage").text()){
                        page=Number(page)+1;
                        $("#currentPage").text(page);
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/animal/animals",
                            data:"id="+id+"&page="+page+"&rows=${param.pageRows}",
                            dataType: "JSON",
                            async: false,
                            success: function (res) {
                                $("#list-cont").empty();
                                for (var i = 0; i < res.length; i++) {
                                    var animal = res[i];

                                    var div = "<div class=\"item\">\n" +
                                        "                                <div class=\"img\">\n" +
                                        "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                                        "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                                        "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"text\">\n" +
                                        "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                                        "                                    <p class=\"price\">\n" +
                                        "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                                        "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                                        "                                    </p>\n" +
                                        "                                </div>\n" +
                                        "                            </div>";
                                    $("#list-cont").append(div);
                                }

                            }
                        });
                    }
                })

            })


        });
        function secondsCategory(secondId) {
            id=secondId;
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/animal/findAnimalCount",
                data:"id="+id,
                dataType: "JSON",
                success:function (res) {
                    $("#count").text(res);
                }
            });
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/animal/totalPage",
                data:"id="+id,
                success:function (res) {
                    $("#totalPage").text(res);
                }
            });
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/animal/animals",
                data:"id="+id+"&page="+page+"&rows=${param.pageRows}",
                dataType: "JSON",
                async: false,
                success: function (res) {
                    $("#list-cont").empty();
                    for (var i = 0; i < res.length; i++) {
                        var animal = res[i];

                        var div = "<div class=\"item\">\n" +
                            "                                <div class=\"img\">\n" +
                            "                                    <a href='${pageContext.request.contextPath}/animal/queryOneById?id="+animal.id+"'><img\n" +
                            "                                            style=\"margin: 50px 100px 50px 50px\"\n" +
                            "                                            src=http://192.168.46.138/"+animal.img+" /></a>\n" +
                            "                                </div>\n" +
                            "                                <div class=\"text\">\n" +
                            "                                    <p class=\"title\">"+animal.title+"</p>\n" +
                            "                                    <p class=\"price\">\n" +
                            "                                        <span class=\"pri\">￥"+animal.price+"</span>\n" +
                            "                                        <span class=\"nub\">"+animal.count+"付款</span>\n" +
                            "                                    </p>\n" +
                            "                                </div>\n" +
                            "                            </div>";
                        $("#list-cont").append(div);
                    }

                }
            });
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

<div class="content content-nav-base commodity-content">
    <%@include file="common/middle.jsp" %>
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
                        <a class="active" href="javascript:;"
                          id="saleCount">销量</a>
                        <a href="javascript:;" event='price' id="price">价格</a>
                        <a href="javascript:;" event='newprod' id="pubDate">新品</a>
                    </div>
                    <div class="prod-number">
                        <span style="color: #9175ad;font-family: '微软雅黑', 'Microsoft Yahei'">
                            <b>一共为您找到<span id="count"></span>只小动物</b>
                        </span>
                    </div>
                    <div class="cont-list layui-clear" id="list-cont">
                    </div>
                    <div id="demo0" style="text-align: center;">
                        <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
                            <a href="javascript:;" id="lastPage"
                               class="layui-laypage-prev ">上一页</a>
                            <a href="javascript:;">第<span id="currentPage"></span>页/共<span
                                    id="totalPage"></span>页</a>
                            <a href="javascript:;" id="nextPage"
                               class="layui-laypage-next">下一页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>