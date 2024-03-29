<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
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
                                <h1 style="text-align: center;">添加课程信息</h1>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="/admin/addCourse" id="editfrom" method="post">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">课程号</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputEmail3" name="courseId"
                                               placeholder="请输入课程号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">课程名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPassword3" name="courseName"
                                               placeholder="请输入课程名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">授课老师编号</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="teacherId">
                                            <c:forEach items="${teacherList}" var="item">
                                                <option value="${item.userId}">${item.userName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">上课时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="courseTime" placeholder="请输入上课时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">上课地点</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="classRoom" placeholder="上课地点">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">周数</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="courseWeek" placeholder="请输入周数">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="coursetype">课程的类型：</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="courseType">
                                            <option value="必修课">必修课</option>
                                            <option value="选修课">选修课</option>
                                            <option value="公共课">公共课</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="collegeId">
                                            <c:forEach items="${collegeList}" var="item">
                                                <option value="${item.collegeId}">${item.collegeName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">学分：</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="score" placeholder="请输入学分">
                                    </div>
                                </div>
                                <div class="form-group" style="text-align: center">
                                    <button class="btn btn-default" type="submit">提交</button>
                                    <button class="btn btn-default" type="reset">重置</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
</script>
</html>