<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">修改课程信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/admin/editCourse" id="editfrom" method="post">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">课程号</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="number" class="form-control" id="inputEmail3"
                                       value="${course.courseId}" name="courseId" placeholder="请输入课程号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">课程名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassword3" name="courseName"
                                       value="${course.courseName}" placeholder="请输入课程名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="grade">授课老师编号</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="teacherId" id="teacherId">
                                    <c:forEach items="${teacherList}" var="item">
                                        <option value="${item.userId}">${item.userName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">上课时间</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="courseTime" value="${course.courseTime}"
                                       placeholder="请输入上课时间">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">上课地点</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="classRoom" value="${course.classRoom}"
                                       placeholder="上课地点">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">周数</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="courseWeek" value="${course.courseWeek}"
                                       placeholder="请输入周数">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="courseType">课程的类型：</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="courseType" id="courseType">
                                    <option value="必修课">必修课</option>
                                    <option value="选修课">选修课</option>
                                    <option value="公共课">公共课</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
                            <div class="col-sm-10">
                                <%--隐藏域--%>
                                <input type="hidden" id="selectedcollegeId" value="${course.collegeId}"/>
                                <select class="form-control" name="collegeId" id="collegeList-select">
                                    <c:forEach items="${collegeList}" var="item">
                                        <option value="${item.collegeId}">${item.collegeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">学分：</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="score" value="${course.score}"
                                       placeholder="请输入学分">
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
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active");
    $(document).ready(function () {
        var key1 = $("#selectedcollegeId").val();
        //根据值让option选中
        $("#collegeList-select option[value='" + key1 + "']").attr("selected", "selected");
        var key2 = '${course.courseType}';
        $("#courseType option[value='" + key2 + "']").attr("selected", "selected");
        var key3 = '${course.teacherId}';
        $("#teacherId option[value='" + key3 + "']").attr("selected", "selected");
    });
</script>
</html>