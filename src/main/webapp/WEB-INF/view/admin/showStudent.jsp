<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生信息显示</title>
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
                            <h1 class="col-md-5">学生名单管理</h1>
                            <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;"
                                  action="/admin/selectStudent" id="form1" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="请输入姓名" name="findByName">
                                    <span class="input-group-addon btn" id="sub">搜索</span>
                                </div>
                            </form>
                            <button class="btn btn-default col-md-2" style="margin-top: 20px"
                                    onClick="location.href='/admin/addStudent'">
                                添加用户信息
                                <sapn class="glyphicon glyphicon-plus"/>
                            </button>

                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>出生年份</th>
                            <th>入学时间</th>
                            <th>学院</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${studentList}" var="item">
                            <tr>
                                <td>${item.userId}</td>
                                <td>${item.userName}</td>
                                <td>${item.sex}</td>
                                <td><fmt:formatDate value="${item.birthYear}" dateStyle="medium"/></td>
                                <td><fmt:formatDate value="${item.grade}" dateStyle="medium"/></td>
                                <td>${item.collegeName}</td>
                                <td style="text-align: center">
                                    <button class="btn btn-default btn-xs btn-info"
                                            onClick="location.href='/admin/editStudent?id=${item.userId}'">修改
                                    </button>
                                    <button class="btn btn-default btn-xs btn-danger btn-primary"
                                            onClick="location.href='/admin/removeStudent?id=${item.userId}'">删除
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
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo-1}">上一页</a>
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
                                            <a href="/admin/showStudent?page=${index}">${index}</a>
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
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo+1}">下一页</a>
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