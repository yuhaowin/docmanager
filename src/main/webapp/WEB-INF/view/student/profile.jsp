<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/style.css" media="all"/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrapper" class="toggled">
    <!-- 顶栏 -->
    <jsp:include page="top.jsp"></jsp:include>
    <%--边栏sidebar--%>
    <jsp:include page="sidebar.jsp"></jsp:include>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <h1 style="text-align: center;">个人信息</h1>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form name="reset" class="form-horizontal" role="form" action="/student/profile_update"
                                  id="editfrom"
                                  method="post" onsubmit="return check()">
                                <div class="form-group">
                                    <label for="userID" class="col-sm-2 control-label">学号</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="userId" id="userID"
                                               value="${student.userId}" disabled>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="userName" class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="userName" id="userName"
                                               placeholder="请输入姓名" value="${student.userName}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="sex" class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="sex" id="sex"
                                               placeholder="请输入性别" value="${student.sex}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="birthYear" class="col-sm-2 control-label">出生日期</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="birthYear" id="birthYear"
                                               placeholder="请输入出生日期" value=<fmt:formatDate  value="${student.birthYear}" pattern="yyyy-MM-dd HH:mm:ss"/>>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="grade" class="col-sm-2 control-label">入学时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="grade" id="grade"
                                               placeholder="请输入入学时间" value=<fmt:formatDate value="${student.grade}" pattern="yyyy-MM-dd HH:mm:ss"/> disabled>
                                    </div>
                                </div>

                                <%--<div class="form-group">--%>
                                    <%--<label for="collegeID" class="col-sm-2 control-label">所属学院代码</label>--%>
                                    <%--<div class="col-sm-10">--%>
                                        <%--<input type="text" class="form-control" name="collegeId" id="collegeID"--%>
                                               <%--placeholder="请输入所属学院代码" value="${student.collegeId}">--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="collegeId" id = "college" disabled>
                                            <c:forEach items="${collegeList}" var="item">
                                                <option value="${item.collegeId}">${item.collegeName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group" style="text-align: center">
                                    <button class="btn btn-default" type="submit">提交</button>
                                    <button class="btn btn-default" type="reset">重置</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function check() {
        if (reset.oldPassword.value == "" || reset.oldPassword.value == null) {
            alert("请输入旧账户密码");
            return false;
        }
        if (reset.password1.value == "" || reset.password1.value == null) {
            alert("请输入重置密码");
            return false;
        }
        if (reset.password2.value == "" || reset.password2.value == null) {
            alert("请输入再次输入密码");
            return false;
        }
        if (reset.password1.value != reset.password2.value) {
            alert("两次密码不正确");
            return false;
        }
    }
    var collegeSelect = $("#college option");
    for (var i = 0; i < collegeSelect.length; i++) {
        if (collegeSelect[i].value == '${student.collegeId}') {
            collegeSelect[i].selected = true;
        }
    }
</script>
</html>