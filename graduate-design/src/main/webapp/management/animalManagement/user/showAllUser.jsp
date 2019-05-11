<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            text: '修改状态【0/1】',
            iconCls: 'icon-edit',
            handler: function () {
                //获取指定行
                var row = $("#userTable").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#userTable").edatagrid("getRowIndex", row);
                    $("#userTable").edatagrid("editRow", index);
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
                $("#userTable").edatagrid("saveRow");
                $.messager.show({
                    title: "系统提示",
                    msg: "修改成功"
                });
            }
        }, {
            text: '导出',
            iconCls: 'icon-save',
            handler: function () {
                location.href = "${pageContext.request.contextPath}/consumer/exportUser";
            }
        }, {
            text: '导入',
            iconCls: 'icon-save',
            handler: function () {
                $.post(
                    "${pageContext.request.contextPath}/consumer/userImport",
                    function () {
                        $.messager.show({
                            title: "系统提示",
                            msg: "导入成功"
                        })
                    }
                );
            }
        }]


        $("#userTable").edatagrid({
            fitColumns: true,
            fit: true,
            singleSelect: true,
            url: "${pageContext.request.contextPath}/consumer/queryAllUser",
            updateUrl: "${pageContext.request.contextPath}/consumer/updateUser?data1=" + "近一周" + "&data2=" + "近二周" + "&data3=" + "近三周",
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20, 25],
            toolbar: toolbar,
            columns: [[
                {field: 'phone', title: '电话', width: 1},
                {
                    field: 'status', title: '状态', width: 1, formatter: showStatus,
                    editor: {type: "text", options: {required: true}}//添加该属性后，表示该列可以双击进行修改
                },
                {field: 'name', title: '昵称', width: 1},
                {field: 'dharma', title: '真实姓名', width: 1},
                {field: 'sex', title: '性别'}
            ]],

            view: detailview,//表示表格可以收缩查看详细内容
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/upload/' + rowData.headPic + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>注册时间: ' + rowData.registDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    });

    function showStatus(value, row, index) {
        if (value == 1) {
            return "未冻结";
        } else {
            return "已冻结";
        }
    }
</script>
<table id="userTable"></table>
