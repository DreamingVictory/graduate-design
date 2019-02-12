<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="text/javascript">
    $(function () {
        $("#title").textbox({
            required: true
        });

        $("#parentId").combobox({
            editable: false,
            url: "${pageContext.request.contextPath}/category/queryAllCategory",
            valueField: "id",
            textField: "title",
            onLoadSuccess: function (data) {
                $("#categoryId").combobox("setValue", data[0].id);
            }
        });


        $("#addTypeReset").linkbutton({
            text: "重置",
            iconCls: "icon-redo",
            onClick: function () {
                $("#animalCategoryForm").form("reset");
            }
        });
        $("#addTypeSubmit").linkbutton({
            text: "提交",
            iconCls: "icon-ok",
            onClick: function () {
                $("#animalCategoryForm").form(
                    "submit",
                    {
                        url: "${pageContext.request.contextPath}/category/insertCategory",
                        onSubmit: function () {
                            return $("#animalCategoryForm").form("validate");
                        },
                        success: function () {

                            $.messager.show({
                                title: "系统提示",
                                msg: "添加成功"
                            });
                            $("#typeDatagrid").treegrid("reload");
                        }
                    }
                );
            }
        });

    })
</script>
<form id="animalCategoryForm" method="post">
    <table align="center" style="padding-top: 60px">
        <tr></tr>
        <tr>
            <td>标题</td>
            <td>
                <input id="title" name="title"/>
            </td>
        </tr>
        <tr>
            <td>选择父级</td>
            <td>
                <input id="parentId" name="parentId"/>
            </td>
        </tr>


        <tr>
            <td><a id="addTypeSubmit"></a></td>
            <td><a id="addTypeReset"></a></td>
        </tr>
    </table>
</form>