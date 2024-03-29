<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                                <h1 style="text-align: center;">修改教师信息</h1>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="/admin/editTeacher" id="editfrom" method="post">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">工号</label>
                                    <div class="col-sm-10">
                                        <input readonly="readonly" type="number" class="form-control" id="inputEmail3"
                                               name="userId" placeholder="请输入工号" value="${teacher.userId}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPassword3" name="userName"
                                               placeholder="请输入姓名" value="${teacher.userName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
                                    <div class="col-sm-10">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="男">男
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="女">女
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">出生年份</label>
                                    <div class="col-sm-10">
                                        <input type="date"
                                               value="<fmt:formatDate value="${teacher.birthYear}" dateStyle="medium" pattern="yyyy-MM-dd" />"
                                               name="birthYear"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="degree">学历：</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="degree" id="degree">
                                            <option value="本科">本科</option>
                                            <option value="硕士">硕士</option>
                                            <option value="博士">博士</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="title">职称：</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="title" id="title">
                                            <option value="普通教师">普通教师</option>
                                            <option value="助教">助教</option>
                                            <option value="讲师">讲师</option>
                                            <option value="副教授">副教授</option>
                                            <option value="教授">教授</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">入职时间</label>
                                    <div class="col-sm-10">
                                        <input type="date"
                                               value="<fmt:formatDate value="${teacher.grade}" dateStyle="medium" pattern="yyyy-MM-dd" />"
                                               name="grade"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="collegeId" id="college">
                                            <c:forEach items="${collegeList}" var="item">
                                                <option value="${item.collegeId}">${item.collegeName}</option>
                                            </c:forEach>
                                        </select>
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
    $("#nav li:nth-child(3)").addClass("active");

    var sex = '${teacher.sex}'
    $("input[name='sex'][value=" + sex + "]").prop("checked", true);

    var collegeSelect = $("#college option");
    for (var i = 0; i < collegeSelect.length; i++) {
        if (collegeSelect[i].value == '${teacher.collegeId}') {
            collegeSelect[i].selected = true;
        }
    }

    var degreeSelect = $("#degree option");
    var titleSelect = $("#title option");

    for (var i = 0; i < degreeSelect.length; i++) {
        if (degreeSelect[i].value == '${teacher.degree}') {
            degreeSelect[i].selected = true;
        }
    }
    for (var i = 0; i < titleSelect.length; i++) {
        if (titleSelect[i].value == '${teacher.title}') {
            titleSelect[i].selected = true;
        }
    }

</script>
</html>