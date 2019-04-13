<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理系统</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/style.css" media="all"/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrapper" class="toggled">
    <jsp:include page="top.jsp"></jsp:include>
    <%--边栏sidebar--%>
    <jsp:include page="sidebar.jsp"></jsp:include>
    <%--主要内容content--%>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">

                <div class="col-md-12 column">
                    <div class="panel-heading">
                        <div class="row">
                            <h1 class="col-md-5">我教授的课程</h1>
                            <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;"
                                  action="/teacher/selectCourse" id="form1" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="请输入课程名" name="findByName">
                                    <span class="input-group-addon btn"
                                          onclick="document.getElementById('form1').submit"
                                          id="sub">搜索</span>
                                </div>
                            </form>
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
                            <th style="text-align: center">操作</th>
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
                                <td style="text-align: center">
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/teacher/gradeCourse?id=${item.courseId}'">成绩
                                    </button>
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/teacher/export?id=${item.courseId}'">导出成绩
                                    </button>
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/teacher/showsubject?id=${item.courseId}'">选题
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <c:if test="${pagingVO != null}">
                        <c:choose>
                            <c:when test="${pagingVO.curentPageNo <= 1}">
                                <!--如果 -->
                                <li class="disabled"><a href="#">上一页</a></li>
                            </c:when>
                            <c:otherwise>
                                <!--否则 -->
                                <li>
                                    <a href="/teacher/showCourse?page=${pagingVO.curentPageNo-1}">上一页</a>
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
                                        <a href="/teacher/showCourse?page=${index}">${index}</a>
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
                                    <a href="/teacher/showCourse?page=${pagingVO.curentPageNo+1}">下一页</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    <%--设置菜单中--%>
    $("#nav li:nth-child(1)").addClass("active")
    $("#sub").click(function () {
        $("#form1").submit();
    });
</script>
</html>