<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>课程信息显示</title>
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
                            <h1 class="col-md-5">学生文档</h1>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>文件名</th>
                            <th>提交时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${courseDocList}" var="item">
                            <tr>
                                <td>${item.studentId}</td>
                                <td>${item.fileName}</td>
                                <td><fmt:formatDate value="${item.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                    <a class="btn btn-default btn-xs btn-info" href="/files${item.fileUrl}"
                                       download="${item.fileName}">下载</a>
                                    <a target="_blank" class="btn btn-default btn-xs btn-info"
                                       href="/files/preview?path=${item.fileUrl}">预览</a>
                                    <button class="btn btn-default btn-xs btn-info"
                                            <c:choose>
                                                <c:when test="${item.bak == 0}">
                                                    onClick="location.href='/admin/courseDocBak?subjectId=${item.subjectId}&fileId=${item.fileId}'">归档
                                                </c:when>
                                                <c:when test="${item.bak != 0}">
                                                    >已归档
                                                </c:when>
                                            </c:choose>

                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>