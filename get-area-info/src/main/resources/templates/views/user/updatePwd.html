<!DOCTYPE html>
<html lang="en">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
<body>
修改密码 <a href="/">返回首页</a><br/>
    原密码<input type="password" id="oldPwd"><br/>
    新密码<input type="password" id="newPwd"><br/>
    重复新密码<input type="password" id="repeatPwd"><br/>
<input type="button" id="sumitBtn" value="修改" />

</body>
<script>
    $("#sumitBtn").click(function () {
       let  oldPwd=$("#oldPwd").val();
       let  newPwd=$("#newPwd").val();
       let  repeatPwd=$("#repeatPwd").val();
       if(newPwd != repeatPwd){
            alert('两次输入的新密码不一致');
            return;
       }

        $.ajax({
            url:"/user/updatePwd", //请求的url地址
            dataType:"json", //返回格式为json
            method: "post",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"oldPwd":oldPwd,"newPwd":newPwd}, //参数值
            success:function(data){ //请求成功时处理
                console.info(data);
                alert(data.message);
                if(data.code ==0 ){
                    location.href="/";
                }
            },
            error:function(data){
                //请求出错处理
                console.info(data);
            }
        });
    });

</script>
</html>