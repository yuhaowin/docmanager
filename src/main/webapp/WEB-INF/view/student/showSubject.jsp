<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>选题信息显示</title>
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
                                <h1 class="col-md-5">课题列表</h1>
                            </div>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>描述</th>
                                <th>文档</th>
                                <th>剩余人数</th>
                                <th style="text-align: center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${subjectList}" var="item">
                                <tr>
                                    <td>${item.subjectName}</td>
                                    <td>${item.describe}</td>
                                    <td>${item.fileName}</td>
                                    <td>${item.size}</td>
                                    <td style="text-align: center">
                                        <c:if test="${item.size > 0}">
                                            <button class="btn btn-default btn-xs btn-info"
                                                    onClick="location.href='/student/selectSubject?subjectId=${item.subjectId}&courseId=${item.courseId}'">
                                                选题
                                            </button>
                                        </c:if>
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