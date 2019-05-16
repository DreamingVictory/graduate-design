<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div id="userProfile" style="width: 600px;height:400px;align:center"></div>
<script type="text/javascript">
    $(function () {
        var myChart = echarts.init(document.getElementById('userProfile'));
        var option = {
            title: {
                text: '宠物专属会馆用户分布图',
                subtext: '纯属虚构',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 5,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            }, series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    }
                }, {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    }
                }
            ]

        };
        myChart.setOption(option);
        $.post(
            "${pageContext.request.contextPath}/user/groupBySex",
            "sex=" + "男",
            function (res) {
                myChart.setOption({
                    series: [
                        {
                            data: res
                        }, {}
                    ]
                })
            }, "JSON"
        );
        $.post(
            "${pageContext.request.contextPath}/user/groupBySex",
            "sex=" + "女",
            function (res) {
                myChart.setOption({
                    series: [{},
                        {
                            data: res
                        }
                    ]
                })
            }, "JSON"
        );


        $.post(
            "${pageContext.request.contextPath}/consumer/groupByProvince",
            function (res) {
                myChart.setOption({
                    series: [
                        {
                            data: res
                        }
                    ]
                })
            }, "JSON"
        );


        //修改状态的时候触发goEasy实时更新
        var goEasy = new GoEasy({
            appkey: "BC-866c45c541e54fb6a416e17428daedcd"
        });
        /!*接收消息*!/
        goEasy.subscribe({
            channel: "userProfile",

            onMessage: function (message) {
                myChart.setOption({
                    series: [
                        {
                            data: eval("(" + message.content + ")").male
                        }, {data: eval("(" + message.content + ")").female}
                    ]
                });
            }
        });


    })

</script>