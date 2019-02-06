<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    var album;
    var toolbar = [{
        text: '狗狗详情',
        iconCls: 'icon-add',
        handler: function () {
            var row = $("#animalDatagrid").treegrid("getSelected");
            if (row != null) {
                if (row.children == null) {
                    $.messager.alert(
                        "温馨提示",
                        "请选中专辑，再查看详细信息",
                        "info");
                } else {
                    album = row;
                    $("#animalTypeDialog").dialog("open");
                }

            } else {
                $.messager.alert(
                    "温馨提示",
                    "请先选中专辑,再查看详细信息",
                    "info");
            }
        }

    }, {
        text: '添加专辑',
        iconCls: 'icon-add',
        handler: function () {
            $("#addTypeDialog").dialog("open");
        }
    }, {
        text: '添加章节',
        iconCls: 'icon-add',
        handler: function () {
            $("#addAnimalDialog").dialog("open");
        }
    },  {
        text: '导出',
        iconCls: 'icon-save',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/album/export";
        }
    }]


    $(function () {

        $("#animalDatagrid").treegrid({
            url: "${pageContext.request.contextPath}/category/queryAllCategory",
            idField: "categoryId",
            treeField: "categoryName",
            fitColumns: true,
            fit: true,
            toolbar: toolbar,
           /* pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],*/
            columns: [[
                {field: "title", title: "名字", width: 1},
                {field: "size", title: "章节大小", width: 1},
                {field: "duration", title: "章节时长", width: 1},
                {field: "url", title: "播放", formatter: playRadio}
            ]]
        });
        /*初始化添加专辑对话框*/
        $("#addTypeDialog").dialog({
            width: 500,
            height: 400,
            title: "添加类别",
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/album/addAlbum.jsp"
        });
        /*初始化专辑详情对话框*/
        $("#animalTypeDialog").dialog({
            width: 500,
            height: 400,
            title: "类别详情",
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/album/detailAlbum.jsp"
        });
        /*初始化添加章节对话框*/
        $("#addAnimalDialog").dialog({
            width: 500,
            height: 400,
            title: "添加宠物",
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/album/addChapter.jsp"
        });


    });

    function playRadio(value, row, index) {
        if (row.children == null) {
            return "<audio id='" + row.id + "' src='${pageContext.request.contextPath}/upload/" + value + "'  preload=\"auto\" controls=\"controls\" style='width:20px;height:20px'></audio>";
        }


    }

</script>
<table id="animalDatagrid"></table>
<div id="addTypeDialog"></div>
<div id="aniamlTypeDialog"></div>
<div id="addAnimalDialog"></div>
