<!DOCTYPE html>
<html lang="en">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
<body>
添加用户 <a href="/">返回首页</a><br/>
${Session.user.name}${Session.user.type}
<form id="userForm">
    name:<input name="name"/> <br/>
    alias:<input name="alias"/><br/>
    密码:<input name="password"/><br/>
    公司:
    <select id="companyId" name="companyId" >
        <option value="">请选择公司</option>
        <#list company1 as item>
            <option  value="${item.id !}"  >
                ${item.name !}
            </option>
        </#list>
    </select>
    <br/>
    类型:
    ${(Session.user.type==1)?string('<input name="type"  type="radio" disabled="disabled" checked value="2">普通用户',
    '<input name="type"  type="radio"   checked value="2">普通用户'+
    '<input name="type" type="radio" value="1">管理员') }

    <br/>

</form>

<input type="button" id="sumitBtn" value="增加" />

</body>
<script>
    $("#sumitBtn").click(function () {
        //校验非空

        let companyId=$("#companyId").val();
        if(companyId==''){
            alert('请选择所属公司');return ;
        }
        let name=$("#name").val();
        if(name==''){
            alert('name 不能为空');return ;
        }
        let alias=$("#alias").val();
        if(alias==''){
            alert('name 不能为空');return ;
        }
        let pwd=$("#pwd").val();
        if(pwd==''){
            alert('密码 不能为空');return ;
        }


        $.ajax({
            url:"/user/insert", //请求的url地址
            dataType:"json", //返回格式为json
            method: "post",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:$("#userForm").serialize(), //参数值
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