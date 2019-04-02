<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>课程信息显示</title>
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
                            <h1 class="col-md-5">学生文档</h1>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>文件名</th>
                            <th>提交时间</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${courseDocList}" var="item">
                            <tr>
                                <td>${item.studentId}</td>
                                <td>${item.fileName}</td>
                                <td><fmt:formatDate value="${item.lastTime}" pattern="yyyy年MM月dd日HH点mm分ss秒"/></td>
                                <td style="text-align: center">
                                    <a class="btn btn-default btn-xs btn-info" href="/files${item.fileUrl}"
                                       download="${item.fileName}">下载</a>
                                    <a target="_blank" class="btn btn-default btn-xs btn-info"
                                       href="/files/preview?path=${item.fileUrl}">预览</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%--分页--%>
                <div class="col-md-12 column">
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