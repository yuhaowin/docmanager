<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/style.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/fileinput.css" media="all"/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/fileinput.js" type="text/javascript"></script>
    <script src="/js/fileinput_locale_zh.js" type="text/javascript"></script>
</head>
<style>
    .fileinput-upload {
        display: none !important;
    }
</style>
<body>

<div id="wrapper" class="toggled">
    <!-- 顶栏 -->
    <jsp:include page="top.jsp"></jsp:include>
    <%--边栏sidebar--%>
    <jsp:include page="sidebar.jsp"></jsp:include>


    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <h1 style="text-align: center;">上传文档</h1>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="/student/addFile" id="editfrom" method="post"
                                  enctype="multipart/form-data">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">文档</label>
                                    <div class="col-sm-10">
                                        <div class="form-group">
                                            <input name="file" id="file-1" type="file" data-overwrite-initial="false"
                                                   data-min-file-count="1">
                                            <input name="subjectId" type="hidden" value="${subjectId}"/>
                                            <input name="courseId" type="hidden" value="${courseId}"/>
                                        </div>
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
    $("#nav li:nth-child(2)").addClass("active")
    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: '/files/upload', // you must set a valid URL here else you will get an error
        overwriteInitial: false,
        maxFileSize: 10000,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });
    $(document).ready(function () {
        $("#test-upload").fileinput({
            'showPreview': false,
            'allowedFileExtensions': ['jpg', 'png', 'gif'],
            'elErrorContainer': '#errorBlock'
        });
    });
</script>
</html>