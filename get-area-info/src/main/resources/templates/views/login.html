
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
</head>
<body>
<form action="/login" method="post">
    用户名 : <input type="text" name="name" id="name"/> <br/><br/>
    密码 : <input type="password" name="password"id="password"/> <br/><br/>
    <input type="button" id="submit" value="登录">
    error:<#assign id ='${error !}'>
    ${error !}
</form>

<script>
    $("#submit").click(function () {
        $.ajax({
            url:"/login", //请求的url地址
            dataType:"json", //返回格式为json
            method:"post",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"name":$("#name").val(),"password":$("#password").val()}, //参数值
            success:function(data){

                if(data["code"]==0){
                    location.href='/';
                    return ;
                }
                alert(data.message);
             },
            error:function(data){//请求出错处理
                console.info(data);
            }
        });
    })


</script>
</body>
</html>