<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="text/javascript">
    $(function () {
        $("#title").textbox({
            required: true
        });
        $("#ciurPic").textbox({
            required: true
        });
        $("#oriPic").textbox({
            required:true
        });
        $("#recommand").combobox({
            editable: false,
            required:true
        });
        $("#discount").textbox({
            required:true
        });
        $("#pubDate").datebox({
            required: true,
            editable: false
        });
        $("#img").filebox({
            required: true
        });

        $("#categoryId").combobox({
            editable: false,
            url: "${pageContext.request.contextPath}/category/queryAllSecondCategory",
            valueField: "id",
            textField: "title",
            onLoadSuccess: function (data) {
                $("#categoryId").combobox("setValue", data[0].id);
            }
        });

        $("#addAnimalReset").linkbutton({
            text: "重置",
            iconCls: "icon-redo",
            onClick: function () {
                $("#addAnimalForm").form("reset");
            }
        });
        $("#addAnimalSubmit").linkbutton({
            text: "提交",
            iconCls: "icon-ok",
            onClick: function () {
                $("#addAnimalForm").form(
                    "submit",
                    {
                        url: "${pageContext.request.contextPath}/animal/insertAnimal",
                        onSubmit: function () {
                            return $("#addAnimalForm").form("validate");
                        },
                        success: function () {

                            $.messager.show({
                                title: "系统提示",
                                msg: "添加成功"
                            });
                            $("#animalDatagrid").datagrid("reload");
                        }
                    }
                );
            }
        });

    })
</script>

<form id="addAnimalForm" method="post" enctype="multipart/form-data">
    <table align="center" style="padding-top: 60px">
        <tr></tr>
        <tr>
            <td>标题</td>
            <td>
                <input id="title" name="title"/>
            </td>
        </tr>
        <tr>
            <td>当前价格</td>
            <td>
                <input id="ciurPic" name="ciurPic"/>
            </td>
        </tr>
        <tr>
            <td>原价</td>
            <td>
                <input id="oriPic" name="oriPic"/>
            </td>
        </tr>
        <tr>
            <td>出生时间</td>
            <td>
                <input id="pubDate" name="pubDate"/>
            </td>
        </tr>

        <tr>
            <td>宠物类别</td>
            <td>
                <input id="categoryId" name="categoryId"/>
            </td>
        </tr>
        <tr>
            <td>宠物推荐度</td>
            <td>
                <select id="recommand" class="easyui-combobox" name="recommand" style="width:100px;" >

                    <option>推荐指数1</option>
                    <option>推荐指数2</option>
                    <option>推荐指数3</option>
                    <option>推荐指数4</option>
                    <option>推荐指数5</option>
                    <option>推荐指数6</option>
                    <option>推荐指数7</option>
                    <option>推荐指数8</option>
                    <option>推荐指数9</option>
                    <option>推荐指数10</option>

                </select>
            </td>
        </tr>
        <tr>
            <td>宠物图片</td>
            <td>
                <input id="img" name="file"/>
            </td>
        </tr>
        <tr>
            <td>宠物状态</td>
            <td>
                <input  type="radio" name="status" value="上架" checked/>上架
                <input  type="radio" name="status" value="下架"/>下架
            </td>
        </tr>
        <tr>
            <td>宠物折扣</td>
            <td>
                <input id="discount" name="discount"/>
            </td>
        </tr>

        <tr>
            <td>描述信息</td>
            <td>
                <textarea id="description" cols="30" rows="6" name="description"></textarea>
            </td>
        </tr>


        <tr>
            <td><a id="addAnimalSubmit"></a></td>
            <td><a id="addAnimalReset"></a></td>
        </tr>
    </table>
</form>