<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="text"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
        #image1 {margin-top:32px; border: #0c5460 1px solid}
    </style>
    <script>
        var t=false;
        function changeImage() {
            document.getElementById("image1").src  = "/captcha?t="+new Date().getTime();
        }
        function verify() {
            var v = document.getElementById("aaa").value;
            var xhr = new XMLHttpRequest();
            xhr.onload = function() {
                // x 是结果 取值 true | false
                var x = JSON.parse(xhr.responseText);
                if(x) {
                    document.getElementById("error").innerText="验证成功";
                   t=true;
                } else {
                    document.getElementById("error").innerText="验证失败";
                  t=false;
                }
            };
            xhr.open("get","/checkCaptcha?captcha="+v, true);
            xhr.send();
        }
    </script>
</head>

<body>

<div class="container">
    <form class="form-signin" action="/login" method="post" onsubmit=" return t">
        <h3 class="form-signin-heading">请登录</h3>
        <div class="form-row">
            <div class="col-md-12">
                <label for="username">用户名</label>
                <input type="text" class="form-control is-valid" id="username" name="username" placeholder="请输入用户名" value="admin" required>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-12">
                <label for="password">密码</label>
                <input type="password" class="form-control is-valid" id="password" name="password" placeholder="请输入密码" value="123" required>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-4">
                <label for="password">验证码</label>
                <input type="text" id="aaa" name="captcha" class="form-control is-valid" placeholder="验证码" onkeyup="verify()">
                <div id="error" class="valid-feedback"></div>
            </div>
            <div class="col-md-4">
                <img src="/captcha" onclick="changeImage()" id="image1"/>
            </div>
        </div>
        <br/>
        <div class="form-row">
            <div class="col-md-12">
                <button class="btn btn-lg btn-primary btn-block" type="submit" onsubmit=false>登录</button>
            </div>
        </div>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug
<script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
-->
</body>
</html>