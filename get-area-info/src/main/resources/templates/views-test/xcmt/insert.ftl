<html>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
<body>
<div class="container">
    <div class="row">
        <h1></h1>
        上传模图  <a href="/">首页</a><br/>
        cad文件 <input type="file" name="file" id="file" value="" placeholder="cad文件"><br/>
        缩略图<input type="file" name="img" id="img" value="" placeholder="图片"><br/>
        所属公司:${Session.user.company.name !}
        <input id="id" type="hidden" value="${Session.user.companyId !}"/><br/>
        模图名称: <input id="name" ><br/>
        模图类型:<select id="xctypeId" >
            <#list xctype as item>
                <option value="${item.id !}">
                    ${item.name !}
                </option>
            </#list>
        </select><br/>
        工厂:<select id="companyGc" >
            <#list company0 as item>
                <option value="${item.id !}">
                    ${item.name !}
                </option>
            </#list>
        </select>
        <br/>
        <input type="button" onclick="postData(0);" value="保存" name="" style="width:100px;height:30px;">
        <input type="button" onclick="postData(1);" value="保存草稿" name="" style="width:100px;height:30px;">
    </div>
</div>

    <script type="text/javascript">
        function postData(draft){

            let formData = new FormData();
            formData.append("file",$("#file")[0].files[0]);
            formData.append("img",$("#img")[0].files[0]);
            formData.append("xctypeId",$("#xctypeId").val());
            formData.append("companyGc",$("#companyGc").val());
            formData.append("name",$("#name").val());
            formData.append("draft",draft);
            $.ajax({
                url:'/xcmt/insert', /*接口域名地址*/
                type:'POST',
                data: formData,
                contentType: false,
                processData: false,
                success:function(res){
                    console.log(res);
                    alert(res["message"]);
                    if(res["code"]==0){
                        location.href='/xcmt/list';
                    }
                },
                error:function (er){
                    console.info(er);
                    alert('error:'+er);
                }
            })
        }
</script>
</body>
</html>