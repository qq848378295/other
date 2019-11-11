<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        用户列表 <a href="/">返回首页</a> &nbsp;  <a href="/user/insert">增加用户</a>
    <input  type="hidden" value="true" id="hasNextPage">
        <input  type="hidden" value="0" id="pageNum">
        <div class="col-md-2">
                <table id="dataTable" border="1" class="table" >
                    <tr>
                        <td>id</td>
                        <td>姓名</td>
                        <td>简称</td>
                        <td>类型</td>
                        <td>公司</td>
                        <td>禁止登陆</td>
                        <td>是否删除</td>
                        <td>操作</td>
                    </tr>
            </table>
                <input type="button" id="moreBtn" style="display:block" value="加载更多">
         </div>
    </div>

    <div id="resetPwd">
        重置密码<br/>
        用户id:<input id="id" >
        新密码:<input id="pwd">
        <input type="button" id="resetPwdBtn" value="重置密码" />
    </div>

</div>
<script  >

    function getData(){
        let pageNum=$("#pageNum").val();
        pageNum=parseInt(pageNum)+1;
        $("#pageNum").val(pageNum);

        $.ajax({
            url:"/user/list", //请求的url地址
            dataType:"json", //返回格式为json
            method:"post",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"pageNum":pageNum}, //参数值
            success:function(data){ //请求成功时处理
                if(data.code==0){
                    console.info(data.data);
                    console.info(data.data.hasNextPage);
                    if(data.data.hasNextPage==false){
                        $("#moreBtn").css("display","none")
                    }
                    $.each(data.data.list, function(i,o) {
                        //id 姓名 name 类型 公司 禁止登陆 是否删除 操作
                        <#--name,alias,create_time,last_login_time,type,company,enable,del-->
                        let tr=
                            "<tr>" +
                            "<td>"+o["id"]+"</td>" +
                            "<td>"+o["name"]+"</td>" +
                            "<td>"+o["alias"]+"</td>" +
                            "<td>"+
                            (o["type"]==0?"超级管理员":o["type"]==1?"普通管理员":"普通用户")+
                            "</td>" +
                            "<td>"+(o["company"]==null?"":o["company"]["name"])+"</td>" +
                            "<td>"+
                                (o["enable"]?"正常":"禁止")+
                                "&nbsp;<a href='javascript:enable("+o["id"]+","+(!o["enable"])+")'>"+ ( (!o["enable"])?"正常":"禁止")+"</a>"+
                            "</td>" +
                            "<td>"+
                                (o["del"]?"删除":"正常")+
                                "&nbsp;<a href='javascript:del("+o["id"]+","+(!o["del"])+")'>"+( (!o["del"])?"删除":"正常")+"</a>"+
                            "</td>" +
                            "<td><a href=\"javascript:resetPwd('"+o.id+"');\">重置密码</a></td>"+
                            "</tr>";
                        $("#dataTable").append(tr)
                    });
                }
            },
            error:function(data){  //请求出错处理
                console.info(data);
            }
        });
    }
    $("#moreBtn").click(function (){
         console.info("click getData()")
        getData();
    });
    function  resetPwd(id){
        $("#id").val(id);
    }
    function enable(id,enable){
        $.ajax({
            url: "/user/enable", //请求的url地址
            dataType: "json", //返回格式为json
            method: "POST",
            async: true,
            data: {"id":id,"enable":enable},
            success: function (data) {//请求成功时处理
                if(data["code"]==0){
                    location.reload();
                }else{
                    alert(data["message"]);
                }
            }
        });
    }

    function del(id,del){
        $.ajax({
            url: "/user/del", //请求的url地址
            dataType: "json", //返回格式为json
            method: "POST",
            async: true,
            data: {"id":id,"del":del},
            success: function (data) {//请求成功时处理
                if(data["code"]==0){
                    location.reload();
                }else{
                    alert(data["message"]);
                }
            }
        });
    }


    $("#resetPwdBtn").click(function (){
        let id=$("#id").val();
        let pwd=$("#pwd").val();
        if(id=='' || pwd==''){
            alert('不能为空');
            return ;
        }
        $.ajax({
            url:"/user/resetPwd", //请求的url地址
            dataType:"json", //返回格式为json
            method:'post',
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"id":id,"password":pwd}, //参数值
            success:function(req){
                //请求成功时处理
                console.info(req);
            },
            error:function(data){
                //请求出错处理
                console.info(data);
            }
        });
    });
    getData();
</script>


</body>
</html>