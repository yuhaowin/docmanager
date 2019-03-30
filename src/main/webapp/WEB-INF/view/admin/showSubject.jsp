<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>选题信息显示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
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
                        <h1 class="col-md-5">选题列表</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>名称</th>
                        <th>描述</th>
                        <th>文档</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${classSubjectList}" var="item">
                        <tr>
                            <td>${item.subjectId}</td>
                            <td>${item.subjectName}</td>
                            <td>${item.describe}</td>
                            <td>${item.fileName}</td>
                            <td><fmt:formatDate value="${item.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <a class="btn btn-default btn-xs btn-info" href="/files${item.fileUrl}"
                                   download="${item.fileName}">下载</a>
                                <button class="btn btn-default btn-xs btn-info"
                                        onClick="location.href='/admin/showCourseDoc?subjectId=${item.subjectId}'">提交信息
                                </button>
                                <a target="_blank" class="btn btn-default btn-xs btn-info"
                                   href="/files/preview?path=${item.fileUrl}">预览</a>
                                <button class="btn btn-default btn-xs btn-info"
                                        <c:choose>
                                            <c:when test="${item.bak == 0}">
                                                onClick="location.href='/admin/subjectBak?subjectId=${item.subjectId}&courseId=${courseId}'">归档
                                            </c:when>
                                            <c:when test="${item.bak != 0}">
                                                >已归档
                                            </c:when>
                                        </c:choose>

                                </button>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/admin/showStudent?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo-2 > 0}">
                                    <li>
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo-2}">${pagingVO.curentPageNo-2}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo - 1 > 0}">
                                    <li>
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo-1}">${pagingVO.curentPageNo-1}</a>
                                    </li>
                                </c:if>
                                <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                                <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                    <li>
                                        <a href="/admin/showStudent?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                    </li>
                                </c:if>
                                <li><a href="/admin/showStudent?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                            </ul>
                        </nav>
                    </c:if>
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

    function confirmd() {
        var msg = "您真的确定要删除吗？！";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    }
    ;

    $("#sub").click(function () {
        $("#form1").submit();
    });

    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:last-child").addClass("disabled")
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    }
    ;
    </c:if>
</script>
</html>