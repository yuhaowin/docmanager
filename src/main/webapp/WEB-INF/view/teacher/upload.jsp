<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
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
<div class="container kv-main">
    <form enctype="multipart/form-data">
        <div class="form-group">
            <input name="file" id="file-1" type="file" data-overwrite-initial="false" data-min-file-count="1">
        </div>
    </form>
    <br>
</div>
</body>
<script>
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