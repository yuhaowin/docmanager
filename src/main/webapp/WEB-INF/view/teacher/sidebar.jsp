<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                管理系统
            </a>
        </li>
        <li>
            <a href="/teacher/showCourse"><i class="fa fa-fw fa-list-alt"></i>我的课程</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i>个人管理<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">ACTIVE</li>
                <li><a href="/teacher/passwordRest">修改密码</a></li>
                <li><a href="/teacher/profile">个人信息</a></li>
            </ul>
        </li>
        <li>
            <a href="/logout"><i class="fa fa-fw fa-list-alt"></i>退出系统</a>
        </li>
        <li class="disabled">
            <a href="##">实践教学材料提交系统</a>
        </li>
    </ul>
</nav>