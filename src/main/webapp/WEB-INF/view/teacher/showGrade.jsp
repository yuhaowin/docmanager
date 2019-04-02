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
                            <h1 class="col-md-5">已选该课程学生名单</h1>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>分数</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${selectedCourseList}" var="item">
                            <tr>
                                <td>${item.studentCustom.userId}</td>
                                <td>${item.studentCustom.userName}</td>
                                <c:if test="${!item.over}">
                                    <td>未打分</td>
                                    <td>
                                        <button class="btn btn-default btn-xs btn-info"
                                                onClick="location.href='/teacher/mark?studentId=${item.studentId}&courseId=${item.courseId}'">
                                            打分
                                        </button>
                                    </td>
                                </c:if>
                                <c:if test="${item.over}">
                                    <td>${item.mark}</td>
                                    <td>
                                        <button class="btn btn-default btn-xs btn-info"
                                                onClick="location.href='/teacher/mark?studentId=${item.studentId}&courseId=${item.courseId}'">
                                            修改
                                        </button>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%--分页--%>
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
                                    <a href="/teacher/gradeCourse?id=${id}&page=${pagingVO.curentPageNo-1}">上一页</a>
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
                                        <a href="/teacher/gradeCourse?id=${id}&page=${index}">${index}</a>
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
                                    <a href="/teacher/gradeCourse?id=${id}&page=${pagingVO.curentPageNo+1}">下一页</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
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