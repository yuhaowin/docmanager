<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>课程信息显示</title>
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
                    <div class="panel-heading">
                        <div class="row">
                            <h1 class="col-md-5">课程列表</h1>
                        </div>
                    </div>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>课程号</th>
                            <th>课程名称</th>
                            <th>授课老师编号</th>
                            <th>上课时间</th>
                            <th>上课地点</th>
                            <th>周数</th>
                            <th>课程类型</th>
                            <th>学分</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${courseList}" var="item">
                            <tr>
                                <td>${item.courseId}</td>
                                <td>${item.courseName}</td>
                                <td>${item.teacherId}</td>
                                <td>${item.courseTime}</td>
                                <td>${item.classRoom}</td>
                                <td>${item.courseWeek}</td>
                                <td>${item.courseType}</td>
                                <td>${item.score}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/student/stuSelectedCourse?id=${item.courseId}'">选课
                                    </button>
                                    <!--弹出框-->
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
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
                                    <a href="/student/showCourse?page=${pagingVO.curentPageNo-1}">上一页</a>
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
                                        <a href="/student/showCourse?page=${index}">${index}</a>
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
                                    <a href="/student/showCourse?page=${pagingVO.curentPageNo+1}">下一页</a>
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