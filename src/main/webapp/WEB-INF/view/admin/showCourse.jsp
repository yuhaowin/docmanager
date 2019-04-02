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
                            <h1 class="col-md-5">课程名单管理</h1>
                            <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;"
                                  action="/admin/selectCourse" id="form1" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="请输入课程名" name="findByName">
                                    <span class="input-group-addon btn"
                                          onclick="document.getElementById('form1').submit"
                                          id="sub">搜索</span>
                                </div>
                            </form>
                            <button class="btn btn-default col-md-2" style="margin-top: 20px"
                                    onClick="location.href='/admin/addCourse'">
                                添加课程信息
                                <sapn class="glyphicon glyphicon-plus"/>
                            </button>
                        </div>
                    </div>
                    <table class="table table-bordered">
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
                                            onClick="location.href='/admin/editCourse?id=${item.courseId}'">修改
                                    </button>
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/admin/showSubject?courseId=${item.courseId}'">课题
                                    </button>
                                    <button class="btn btn-default btn-xs btn-danger btn-primary"
                                            onClick="location.href='/admin/removeCourse?id=${item.courseId}'">删除
                                    </button>
                                </td>
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
                                    <a href="/admin/showCourse?page=${pagingVO.curentPageNo-1}">上一页</a>
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
                                        <a href="/admin/showCourse?page=${index}">${index}</a>
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
                                    <a href="/admin/showCourse?page=${pagingVO.curentPageNo+1}">下一页</a>
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