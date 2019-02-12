<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">

    var toolbar = [ {
        text: '添加类别',
        iconCls: 'icon-add',
        handler: function () {
            $("#animalFirstTypeDialog").dialog("open");
        }
    },{
        text: '导出',
        iconCls: 'icon-save',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/album/export";
        }
    }]


    $(function () {

        $("#typeDatagrid").treegrid({
            url: "${pageContext.request.contextPath}/category/queryAllCategory",
            idField: "id",
            treeField: "title",
            fitColumns: true,
            fit: true,
            striped:true,
            rownumbers:true,
            toolbar: toolbar,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            columns: [[
                {field: "title", title: "名字", width: 1},
                {field: "parentId", title: "父级Id"}

            ]]
        });
        /*初始化添加专辑对话框*/
        $("#animalFirstTypeDialog").dialog({
            width: 500,
            height: 400,
            title: "添加类别",
            closed: true,
            cache: false,
            href: "${pageContext.request.contextPath}/management/animalManagement/category/addCategory.jsp"
        });

    });



</script>
<table id="typeDatagrid"></table>
<div id="animalFirstTypeDialog"></div>
