<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">我的成绩</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">课程</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="studentId"
                                   id="inputEmail3" value="${courseName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">成绩</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" name="name" class="form-control"
                                   id="inputPassword3"
                                   <c:choose>
                                    <c:when test="${mark != null}">
                                    value="${mark}"
                                    </c:when>
                                    <c:when test="${mark == null}">
                                   value="暂无成绩"
                                    </c:when>
                                    </c:choose>
                                    >
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script>
    $("#nav li:nth-child(2)").addClass("active")
</script>
</html>