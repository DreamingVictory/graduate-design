<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    var toolbar = [{
        text: '添加宠物',
        iconCls: 'icon-add',
        handler: function () {
            $("#addAnimalDialog").dialog("open");
        }
    },{
        text: '修改宠物',
        iconCls: 'icon-redo',
        handler: function () {
            //获取指定行
            var row = $("#animalDatagrid").edatagrid("getSelected");
            if (row != null) {
                //编辑指定行
                var index = $("#animalDatagrid").edatagrid("getRowIndex", row);
                $("#animalDatagrid").edatagrid("editRow", index);
            } else {
                //请先指定行
                $.messager.alert(
                    "温馨提示",
                    "请先选中行，再进行修改",
                    "info"
                )
            }
        }
    }, {
        text: '保存状态',
        iconCls: 'icon-save',
        handler: function () {
            $("#animalDatagrid").edatagrid("saveRow");
            $.messager.show({
                title: "系统提示",
                msg: "修改成功"
            });
        }
    }]


    $(function () {

        $("#searchbox").textbox({
            prompt: "请填写关键词"
        });
        $("#searchBtn").linkbutton({
            iconCls: "icon-search",
            text: "搜索一下，你就知道",
            plain: true,
            width: 200,
            height: 30,
            onClick: function () {
                $.post(
                    "${pageContext.request.contextPath}/animal/queryAllLucene",
                    "params=" + $("#searchbox").val(),
                    function (res) {
                        console.log(res);
                        if (res == "") {
                            $.messager.alert(
                                "系统提示",
                                "没有找到匹配文件哦，请重新输入关键词！",
                                "info"
                            )

                        } else {
                            $("#thead").empty();
                            for (var i = 0; i < res.length; i++) {

                                var tr1 = $("<tr><td>ID：</td><td>" + res[i].id + "</td></tr>");
                                var tr2 = $("<tr><td>标题：</td><td>" + res[i].title + "</td></tr>");

                                var tr3 = $("<tr><td>图片：</td><td><img src='http://192.168.46.138/" + res[i].img + "'/></td></tr>");
                                var tr4 = $("<tr><td>描述：</td><td>" + res[i].description + "</td></tr>");
                                var tr5 = $("<tr><td>上架日期：</td><td>" + res[i].pubDate + "</td></tr>");
                                var hr = $("<tr><td colspan='2'><hr width='500px'></td></tr>");
                                $("#thead").append(tr1).append(tr2).append(tr3).append(tr4).append(tr5).append(hr);

                            }
                        }

                    }
                );
            }
        });

        $("#animalDatagrid").edatagrid({
            url: "${pageContext.request.contextPath}/animal/queryAllAnimal",
            updateUrl: "${pageContext.request.contextPath}/animal/updateAnimal",
            fitColumns: true,
            //fit: true,
            toolbar: toolbar,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            columns: [[
                {field: "title", title: "名字", width: 1},
                {field: "ciurPic", title: "当前价格", width: 1},
                {field: "oriPic", title: "原价", width: 1},
                {
                    field: "status", title: "状态", width: 1,
                    editor: {type: "text", options: {required: true}}//添加该属性后，表示该列可以双击进行修改
                },
                {field: "count", title: "销量"}
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="http://192.168.46.138/' + rowData.img + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>折扣: ' + rowData.discount + '</p>' +
                    '<p>描述信息: ' + rowData.description  + '</p>' +
                    '<p>出生日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });
        /*初始化添加专辑对话框*/
        $("#addAnimalDialog").dialog({
            width: 500,
            height: 400,
            title: "添加宠物",
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/management/animalManagement/animal/addAnimal.jsp"
        });


    });

</script>
<div align="center">
    <input id="searchbox" type="text" style="width:500px;height: 40px;padding-left: auto"></input>
    <a id="searchBtn"></a>
</div>
<table id="animalDatagrid"></table>
<div id="addAnimalDialog"></div>
<hr>
<div id="lucene">
    <table id="luceneTable" align="center">
        <thead id="thead"></thead>
    </table>
</div>

