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
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <h1 class="col-md-5">历史文档</h1>
                                <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;"
                                      action="/admin/searchBakFile" id="form1" method="post">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="输入文件名" name="fileName">
                                        <span class="input-group-addon btn"
                                              onclick="document.getElementById('form1').submit"
                                              id="sub">搜索</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>教师/学生id</th>
                                <th>上传时间</th>
                                <th>文件名称</th>
                                <th>文件归属</th>
                                <th style="text-align: center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item">
                                <tr>
                                    <td>${item.userId}</td>
                                    <td><fmt:formatDate value="${item.lastTime}" pattern="yyyy年MM月dd日HH点mm分ss秒"/></td>
                                    <td>${item.fileName}</td>
                                    <td>${item.fileFrom}</td>
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
                </div>
                <%--分页--%>
                <c:if test="${pagingVO != null}">
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <c:choose>
                                <c:when test="${pagingVO.curentPageNo <= 1}">
                                    <!--如果 -->
                                    <li class="disabled"><a href="#">上一页</a></li>
                                </c:when>
                                <c:otherwise>
                                    <!--否则 -->
                                    <li>
                                        <a href="/admin/bakFile?page=${pagingVO.curentPageNo-1}">上一页</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <!--循环遍历 -->
                            <c:forEach var="index" begin="1" end="${pagingVO.totalCount}" step="1">
                                <c:choose>
                                    <c:when test="${pagingVO.curentPageNo == index}">
                                        <!--如果 -->
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <!--否则 -->
                                        <li>
                                            <a href="/admin/bakFile?page=${index}">${index}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${pagingVO.curentPageNo >= pagingVO.totalCount}">
                                    <!--如果 -->
                                    <li class="disabled"><a href="#">下一页</a></li>
                                </c:when>
                                <c:otherwise>
                                    <!--否则 -->
                                    <li>
                                        <a href="/admin/bakFile?page=${pagingVO.curentPageNo+1}">下一页</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </c:if>
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