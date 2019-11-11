<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.pjax/2.0.1/jquery.pjax.min.js"></script>
</head>
<body>
<div class="container" style="height: 700px;">
    <div class="row">
        模图列表 <br/>
        <a href="/">返回首页</a><br/>
        <a href="/xcmt/insert">新增</a>

        <input  type="hidden" value="true" id="hasNextPage">

        <form id="queryForm">
            每页大小<input id="pageSie" name="pageSize">
            <input  type="hidden"  value="0" id="pageNum" name="pageNum">
            模图类型:<select id="xctypeId" name="xctypeId" >
                <option value="">所有</option>
                <#list xctype as item>
                    <option value="${item.id !}">
                        ${item.name !}
                    </option>
                </#list>
            </select><br/>
            所属工厂:<select id="companyGc"  name="companyGc">
                <option value="">所有</option>
                <#list company0 as item>
                    <option value="${item.id !}">
                        ${item.name !}
                    </option>
                </#list>
            </select>
            所属公司:<select id="companyGs" name="companyGs">
                <option value="">所有</option>
                <#list company1 as item>
                    <option value="${item.id !}">
                        ${item.name !}
                    </option>
                </#list>
            </select>
            检查:<select id="checked" name="checked">
                <option value="">所有</option>
                <option value="false">待检查</option>
                <option value="true">已检查</option>
            </select>
            审核:<select id="accept" name="accept">
                <option value="">所有</option>
                <option value="false>拒绝</option>
                <option value="true">通过</option>
            </select>

        <input type="button" onclick="loadData(1)" value="搜索">

        </form>
        <div class="col-md-2">
                <table  border="1" class="table" >
                    <tr>
                        <td>全选<input type='checkbox' name="checkAll" id="checkAll" ></td>
                        <td>文件名</td>
                        <td>名称</td>
                        <td>图片</td>
                        <td>上传人</td>
                        <td>工厂</td>
                        <td>公司</td>
                        <td>检查状态</td>
                        <td>审核状态</td>
                        <td>创建时间</td>
                        <td>操作</td>
                    </tr>
                    <tbody id="dataTable">

                    </tbody>
            </table>
                <input type="button" id="moreBtn" style="display:none" value="加载更多"><br/>
                <input type="button" id="downloadChecked" value="下载选中的">
         </div>
    </div>

</div>
<script  >
    function loadData(clear){
        if(clear){
            $("#pageNum").val(0);
            $("#dataTable").html('');
        }
        $("#pageNum").val(parseInt($("#pageNum").val())+1);
        $.ajax({
            url:"/xcmt/list", //请求的url地址
            dataType:"json", //返回格式为json
            method:"POST",
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: $('#queryForm').serialize(), //参数值
            success:function(data){ //请求成功时处理
                if(data.code==0){
                    console.info(data.data.hasNextPage);
                    if(data.data.hasNextPage==false){
                        $("#moreBtn").css("display","none");
                        $("#moreBtn").prop("display","none")
                    }else{
                        $("#moreBtn").css("display","block");
                        $("#moreBtn").prop("display","block");
                    }
                    console.info("pageSize:"+data.data.pageSize);
                    $("#pageSie").val(data.data.pageSize);
                    $.each(data.data.list, function(i,o) {
                        let tr=
                            "<tr>" +
                            "<td><input type='checkbox' name='checkId' value='"+o["id"]+"' /> "+o["id"]+"</td>" +
                            "<td><a href='javascript:download({\"ids\":"+o["id"]+"});'>"+o["cadFilename"]+"</a> </td>" +
                            "<td>"+o["name"]+"</td>" +
                            "<td><a href='../"+o["imgFilePath"]+"' target='_blank'><img width='30' height='30' src='../"+o["imgFilePath"]+"'/></a></td>" +
                            "<td>"+o["uploader"]+"</td>" +
                            "<td>"+o["gc"]["name"]+"</td>" +
                            "<td>"+o["gs"]["name"]+"</td>" +
                            "<td>"+(o["checked"]==true?"已检查":"待检查")+"</td>" +
                            "<td>"+(o["accept"]?"通过":(o["accept"]==null?"待审核":"拒绝"))+"</td>" +
                            "<td>"+o["createTime"]+"</td>" +
                            "<td>" +
                                    (o["checked"]==true?"":
                                    "<a href=\"javascript:accept('"+o.id+"',true);\">审核</a>  &nbsp; <a href=\"javascript:accept('"+o.id+"',false);\">拒绝</a>"
                            )+
                            "</td>"+
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
    //下载选中的文件
    $("#downloadChecked").click(function () {
        console.info("download checked");
        let ids="";
        $.each($(":checkbox[name='checkId']:checked"),function(i,o){
            ids+=o.value+",";
        });
        if(ids ==""){
            alert('未选中任何文件');return;
        }
        ids=ids.substr(0,ids.length-1);
        let param={"ids":ids};
        download(param);
       /* $.ajax({
            url: "/xcmt/download", //请求的url地址
            dataType: "json", //返回格式为json
            method: "POST",
            async: true,
            data: {"ids":ids},
            success: function (data) {//请求成功时处理
                if(data["code"]==0){
                    let url=data.data["url"];
                    let param={};
                    open_page(url,param);
                }else{
                    alert(data["message"]);
                }
            }
        });*/

    })

    function accept(id,accept){
        $.ajax({
            url: "/xcmt/accept", //请求的url地址
            dataType: "json", //返回格式为json
            method: "POST",
            async: true,
            data: {"id":id,"accept":accept},
            success: function (data) {//请求成功时处理
                if(data["code"]==0){//修改内容 懒得搞 直接刷新界面
                    location.reload();
                }else{
                    alert(data["message"]);
                }
            }
        });
    }

    function download( param) {
        let url="/download/xcmt/cad";
        let form = '<form action="' + url + '"  target="_blank"  id="windowOpen" style="display:none">';
        for(let key in param) {
            form += '<input name="' + key + '" value="' + param[key] + '"/>';
        }
        form += '</form>';
        $('body').append(form);
        $('#windowOpen').submit();
        $('#windowOpen').remove();
    }


    $("#moreBtn").click(function (){
         console.info("click getData()")
        loadData(0);
    });
    //全选
    $("#checkAll").click(function () {
        if ($("#checkAll").prop("checked") == true) {
            // 上面的复选框已被选中
            $(":checkbox[name='checkId']").prop("checked", true);
        } else {
            // 上面的复选框没被选中
            $(":checkbox[name='checkId']").prop("checked", false);
        }
    });

    loadData();
    
</script>


</body>
</html>