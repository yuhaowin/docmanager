<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                                <h1 style="text-align: center;">添加学生信息</h1>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="/admin/addStudent" id="editfrom" method="post">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">学号</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputEmail3" name="userId"
                                               placeholder="请输入学号"
                                        <c:if test='${student!=null}'>
                                               value="${student.userId}"
                                        </c:if>>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPassword3" name="userName"
                                               placeholder="请输入姓名"
                                        <c:if test='${student!=null}'>
                                               value="${student.userName}"
                                        </c:if>>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-10">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="男" checked>男
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="女">女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">出生年份</label>
                                    <div class="col-sm-10">
                                        <input type="date" value="1996-09-02" name="birthYear"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">入学时间</label>
                                    <div class="col-sm-10">
                                        <input type="date" value="2015-09-02" name="grade"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="collegeId">
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
<script type="text/javascript">
    $("#nav li:nth-child(2)").addClass("active")
</script>
</html>