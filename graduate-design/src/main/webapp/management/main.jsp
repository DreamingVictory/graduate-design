<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>BlingBling萌宠专属会馆</title>
    <link rel="shortcut icon" href="#">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/datagrid-detailview.js "></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.edatagrid.js "></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/function.js "></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/china.js"></script>

    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {

            $.post(
                "${pageContext.request.contextPath}/menu/queryAllMenu",//一级标题和二级标题 自连接
                function (res) {
                    for (var i = 0; i < res.length; i++) {

                        var item = res[i];
                        var array = item.list;
                        var cont = "";
                        for (var j = 0; j < array.length; j++) {//二级标题
                            cont += "<ul><a class='easyui-linkbutton'  data-options=\"iconCls:'" + array[j].iconcls + "'\" " +
                                "onclick=\"addTabs('" + array[j].title + "','" + array[j].iconcls + "','" + array[j].url + "')\" >" + array[j].title + "</a></ul>";
                        }

                        $('#aa').accordion('add', {
                            title: item.title,
                            iconCls: item.iconcls,
                            content: cont,
                            selected: false,
                        });
                    }


                }
            );


        });

        function addTabs(title, iconcls, url) {

            var tab = $("#tt").tabs("exists", title);
            if (tab) {
                $("#tt").tabs("select", title);
            } else {

                $("#tt").tabs("add", {
                    title: title,
                    closable: true,
                    iconCls: iconcls,
                    href: "${pageContext.request.contextPath}" + url,
                });
            }
        }


    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        BlingBling萌宠专属会馆后台管理系统
    </div>
    <div style=" font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:<shiro:principal></shiro:principal>
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              id="logoutUser"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;萌宠会馆 zhaoch@163.com</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url('banner1.jpg');background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>

</body>
</html>