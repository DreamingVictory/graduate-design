$(function () {
    $.extend(
        $.fn.validatebox.defaults.rules,
        {
            isNumber: {
                validator: function (value, params) {
                    return !isNaN(value);
                },
                message: "必须是数字哦"
            },
            isLength: {
                validator: function (value, params) {
                    return value.length == params[0];
                },
                message: "必须是{0}位哦"
            },
            bigLength: {
                validator: function (value, params) {
                    return value.length >= params[0];
                },
                message: "必须大于{0}位哦"
            },
            lessLength: {
                validator: function (value, params) {
                    return value.length <= params[0];
                },
                message: "必须小于{0}位哦"
            }
        }
    );
    $.fn.validatebox.defaults.rules.remote.message = "用户已存在";
});