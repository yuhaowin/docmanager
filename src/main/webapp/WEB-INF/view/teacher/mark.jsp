<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生打分</title>
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
    <%--主要内容content--%>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <%--主体--%>
                <div class="col-md-12 column">
                    <div class="panel-heading">
                        <div class="row">
                            <h1 style="text-align: center;">学生打分</h1>
                        </div>
                    </div>
                    <form name="reset" class="form-horizontal" role="form" action="/teacher/mark" id="editfrom"
                          method="post" onsubmit="return check()">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input readonly="readonly" type="hidden" class="form-control" name="courseId"
                                       id="inputEmail3" value="${selectedCourse.courseId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">学号</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" name="studentId"
                                       id="inputEmail3" value="${selectedCourse.studentId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" name="name" class="form-control"
                                       id="inputPassword3" value="${selectedCourse.studentCustom.userName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">成绩</label>
                            <div class="col-sm-10">
                                <input type="number" name="mark" class="form-control" id="inputPassword3"
                                       placeholder="请输入成绩"
                                <c:if test="${selectedCourse.mark != null}"> value="${selectedCourse.mark}" </c:if> >
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
        if (reset.mark.value == "" || reset.mark.value == null) {
            alert("请输入成绩");
            return false;
        }
    }
</script>
</html>