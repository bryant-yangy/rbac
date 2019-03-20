<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/18 0018
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
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
</head>

<body>
<%@include file="/jsp/nav.jsp" %>
<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/index">首页</a></li>
        <li class="breadcrumb-item">系统管理</li>
        <li class="breadcrumb-item active">用户角色设置</li>
    </ol>
    <div class="row">
        <div class="col-3">
            <ul id="orgTree" class="ztree"></ul>
        </div>
        <div class="col-9">
            <form action="/system/user/modifyrole" method="post">
                <c:forEach items="${roles}" var="role"> <!-- 1, 2, 3, 4, 5-->
                <div class="form-group">
                    <input type="hidden" name="userId" value="${userId}">

                        <!-- List(role(1,系统管理员), role(2,主管)) -->
                        <c:if test="${userRoles.contains(role)}">
                            <input type="checkbox" name="roles" value="${role.id}" checked> ${role.name}
                        </c:if>
                        <c:if test="${not userRoles.contains(role)}">
                            <input type="checkbox" name="roles" value="${role.id}"> ${role.name}
                        </c:if>

                </div>
                </c:forEach>
                <input class="btn btn-info" type="submit" value="保存更改">
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
