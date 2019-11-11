<html>
<body>
<div class="container">
    <div class="row">
            <h1>Hello,${Session.user.name !}.这是一个测试界面views-test</h1>
            <a href="/logout">注销</a><br/>

        <a href="/user/list">用户列表</a>
        <a href="/user/updatePwd?name=${Session.user.name !}">修改密码</a>
        <a href="/company/list">公司列表</a>
        <a href="/xcmt/list">模图列表</a>
        <a href="/swagger-ui.html">api</a>

    </div>
</div>
</body>
</html>