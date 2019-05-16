<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '萌宠专属会馆活跃用户'
        },
        tooltip: {},
        legend: {
            data: ['人数']
        },
        xAxis: {
            data: ["近一周", "近二周", "近三周"]
        },
        yAxis: {}
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.post(
        "${pageContext.request.contextPath}/user/statisCount",
        "data1=" + option.xAxis.data[0] + "&data2=" + option.xAxis.data[1] + "&data3=" + option.xAxis.data[2],
        function (result) {
            console.log(result);
            myChart.setOption({
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: result
                }]
            });
        });

    //修改状态的时候触发goEasy实时更新
    var goEasy = new GoEasy({
        appkey: "BC-866c45c541e54fb6a416e17428daedcd"
    });
    /!*接收消息*!/
    goEasy.subscribe({
        channel: "cmfz",

        onMessage: function (message) {
            myChart.setOption({
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: eval("(" + message.content + ")")
                }]
            });
        }
    });


</script>
