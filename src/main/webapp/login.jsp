<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎登录</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>WELCOME</h1>
            <form class="form" action="/login" method="post">
                <input class="form-input" type="text" name="userName" placeholder="请输入用户名">
                <input class="form-input" type="password" name="passWord" placeholder="请输入密码">
                <button class="form-button" type="submit" id="login-button">登录</button><br><br>
                <input type="radio" name="role" value="admin" class="gcs-radio" id="1" checked/>
                <label for="1"></label>管理员
                <input type="radio" name="role" value="teacher" class="gcs-radio" id="2" />
                <label for="2"></label>教师
                <input type="radio" name="role" value="student" class="gcs-radio" id="3" />
                <label for="3"></label>学生
            </form>
        </div>
    </div>
</div>
<script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>管理系统</h1>
</div>
</body>
</html>