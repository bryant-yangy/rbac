<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>Fixed top navbar example for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style type="text/css">
        body {
            min-height: 75rem;
            padding-top: 4.5rem;
        }
    </style>
    <script>
        var a=false;
        function checkUsername() {
            var xhr = new XMLHttpRequest();
            xhr.onload = function() {
                var exists = xhr.responseText; // 拿到的就是 true | false
                if(exists == "true"){
                    document.getElementById("usernameError").innerText = "该用户已存在";
                    a=false;
                } else {
                    document.getElementById("usernameError").innerText = "该用户名可以使用";
                    a=true;
                }
            };
            xhr.open("get", "/system/user/exists?username="+document.getElementById("username").value, true);
            xhr.send();
        }
    </script>
</head>

<body>

<%@include file="/jsp/nav.jsp" %>

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/index">首页</a></li>
        <li class="breadcrumb-item">系统管理</li>
        <li class="breadcrumb-item active">用户新增</li>
    </ol>
    <div class="row">
        <div class="col-3">
            <ul id="orgTree" class="ztree"></ul>
        </div>
        <div class="col-9">
            <form action="/system/user/add" method="post" onsubmit="return a">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" name="username" class="form-control" id="username"  onblur="checkUsername()" placeholder="至少2位的中英文字符">
                    <span id="usernameError"></span>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" name="password" class="form-control" id="password"
                           placeholder="至少6位的字符数字特殊字符">
                </div>
                <div class="form-group">
                    <label for="orgId">所属机构</label>
                    <input type="text" name="orgId" class="form-control" id="orgId" placeholder="至少2位的中英文字符"
                           value="0">
                </div>
                <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
 ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-3.2.1.slim.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug
<script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
-->
</body>
</html>