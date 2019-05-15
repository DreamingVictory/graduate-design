<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript">
    var toolbar = [{
        text: '修改地址',
        iconCls: 'icon-redo',
        handler: function () {
            //获取指定行
            var row = $("#addressDatagrid").edatagrid("getSelected");
            if (row != null) {
                //编辑指定行
                var index = $("#addressDatagrid").edatagrid("getRowIndex", row);
                $("#addressDatagrid").edatagrid("editRow", index);
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
            $("#addressDatagrid").edatagrid("saveRow");
            $.messager.show({
                title: "系统提示",
                msg: "修改成功"
            });
        }
    }]


    $(function () {


        $("#addressDatagrid").edatagrid({
            url: "${pageContext.request.contextPath}/address/queryAllAddress",
            updateUrl: "${pageContext.request.contextPath}/address/updateAddress",
            fitColumns: true,
            //fit: true,
            toolbar: toolbar,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            columns: [[
                {field: "id", title: "Id", width: 1},
                {   field: "reciveName", title: "收货人", width: 1,
                    editor: {type: "text", options: {required: true}}
                },
                {
                    field: "province", title: "省份", width: 1,
                    editor: {type: "text", options: {required: true}}
                },
                {
                    field: "city", title: "城市", width: 1,
                    editor: {type: "text", options: {required: true}}
                },
                {
                    field: "detailAddress", title: "详细地址", width: 1,
                    editor: {type: "text", options: {required: true}}
                },
                {
                    field: "telphone", title: "电话",
                    editor: {type: "text", options: {required: true}}
                }
            ]]
        });

    });

</script>

<table id="addressDatagrid"></table>

