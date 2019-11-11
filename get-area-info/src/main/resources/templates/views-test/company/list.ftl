<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        公司列表 <a href="/">返回首页</a>
    <input  type="hidden" value="true" id="hasNextPage">
        <input  type="hidden" value="0" id="pageNum">
        <div class="col-md-2">
                <table id="dataTable" border="1" class="table" >
                    <tr>
                        <td>ID</td>
                        <td>名称</td>
                        <td>简称</td>
                        <td>类型</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
            </table>
         </div>
    </div>


</div>
<script  >

    function getData(){
        let pageNum=$("#pageNum").val();
        pageNum=parseInt(pageNum)+1;
        $("#pageNum").val(pageNum);

        $.ajax({
            url:"/company/list", //请求的url地址
            dataType:"json", //返回格式为json
            method:"post",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"pageNum":pageNum}, //参数值
            success:function(data){ //请求成功时处理
                if(data.code==0){
                    $.each(data.data, function(i,o) {
                        let tr=
                            "<tr>" +
                            "<td>"+o["id"]+"</td>" +
                            "<td>"+o["name"]+"</td>" +
                            "<td>"+o["alias"]+"</td>" +
                            "<td>"+(o["type"]==0?"工厂":"公司")+"</td>" +
                            "<td>"+(o["del"]?"删除":"正常")+"</td>" +
                            "<td>" +
                            (o["del"]?
                                    "<a href=\"javascript:del("+o["id"]+",false);\">正常</a></td>"
                                :
                                    "<a href=\"javascript:del("+o["id"]+",true);\">删除</a></td>"
                            )+
                            "</tr>";
                        console.info("tr",tr)
                        $("#dataTable").append(tr)
                    });
                }
            },
            error:function(data){//请求出错处理
                console.info(data);
            }
        });
    }

    getData();

    function del(id,del){
        $.ajax({
            url: "/company/del", //请求的url地址
            dataType: "json", //返回格式为json
            method: "POST",
            async: true,
            data: {"id":id,"del":del},
            success: function (data) {//请求成功时处理
                if(data["code"]==0){//修改内容 懒得搞 直接刷新界面
                    location.reload();
                }else{
                    alert(data["message"]);
                }
            }
        });
    }

</script>


</body>
</html>