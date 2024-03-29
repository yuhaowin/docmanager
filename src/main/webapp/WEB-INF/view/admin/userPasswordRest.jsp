<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<div id="wrapper" class="toggled">
    <!-- 顶栏 -->
    <jsp:include page="top.jsp"></jsp:include>
    <%--边栏sidebar--%>
    <jsp:include page="sidebar.jsp"></jsp:include>
    <%--主要内容content--%>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <%--主体--%>
                <div class="col-md-12 column">
                    <div class="panel-heading">
                        <div class="row">
                            <h1 style="text-align: center;">重置其他用户密码</h1>
                        </div>
                    </div>
                    <form class="form-horizontal" name="reset" role="form" action="/admin/userPasswordRest"
                          id="editfrom" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">账号(非管理员账号)</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="userName" id="inputEmail3"
                                       placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码"
                                       name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password2" id="inputPassword3"
                                       placeholder="请再次输入密码">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default">重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function check() {
        if (reset.username.value == "" || reset.username.value == null) {
            alert("请输入账户名称");
            return false;
        }
        if (reset.password.value == "" || reset.password.value == null) {
            alert("请输入重置密码");
            return false;
        }
        if (reset.password2.value == "" || reset.password2.value == null) {
            alert("请输入再次输入密码");
            return false;
        }
        if (reset.password.value != reset.password2.value) {
            alert("两次密码不正确");
            return false;
        }
    }
</script>
</html>