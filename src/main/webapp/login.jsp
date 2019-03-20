<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>
            <form class="form" action="/login" method="post">
                <input type="text" name="userName" placeholder="请输入用户名">
                <input type="password" name="passWord" placeholder="请输入密码">
                <button type="submit" id="login-button">登陆</button>
            </form>
        </div>
    </div>
</div>

<script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script>
    // $('#login-button').click(function (event) {
    //     event.preventDefault();
    //     $('form').fadeOut(500);
    //     $('.wrapper').addClass('form-success');
    // });
</script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>管理系统</h1>
</div>
</body>
</html>