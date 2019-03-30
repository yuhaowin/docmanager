<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/fileinput.css" media="all"/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/fileinput.js" type="text/javascript"></script>
    <script src="/js/fileinput_locale_zh.js" type="text/javascript"></script>
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
                        <h1 style="text-align: center;">添加选题信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/teacher/addSubject" id="editfrom" method="post" enctype="multipart/form-data" >
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">选题名称</label>
                            <div class="col-sm-10">
                                <input type="hidden" name="courseId" value='${courseId}'/>
                                <input type="text" class="form-control" id="inputEmail3" name="subjectName"
                                       placeholder="请输入选题名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">选题描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPassword3" name="describe"
                                       placeholder="请输入选题描述">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">文档</label>
                            <div class="col-sm-10">
                                <div class="form-group">
                                    <input name="file" id="file-1" type="file" data-overwrite-initial="false" data-min-file-count="1">
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
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
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