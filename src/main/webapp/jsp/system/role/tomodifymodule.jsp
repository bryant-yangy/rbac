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
        function f(a) {
            var div = a.parentNode;
            var checkboxes = div.querySelectorAll(".form-check-input");
            if(a.dataset.check=="false"){
                a.dataset.check="true";
                for(var i = 0; i<checkboxes.length; i ++ ) {
                    checkboxes[i].checked=true;
                }
            }
            else {
                a.dataset.check="false";
                for(var i = 0; i<checkboxes.length; i ++ ) {
                    checkboxes[i].checked =false
                }
            }

        }
    </script>
</head>

<body>
<%@include file="/jsp/nav.jsp" %>

<div class="container">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/index.jsp">首页</a></li>
        <li class="breadcrumb-item">系统管理</li>
        <li class="breadcrumb-item active">修改模块</li>
    </ol>
    <div class="row">
        <div class="col-3">
            <ul id="orgTree" class="ztree"></ul>
        </div>
        <div class="col-9">
            <form action="/system/role/modifymodule" method="post">
                <input type="hidden" name="roleId" value="${param.id}"/>
                <c:forEach items="${modules}" var="module">
                    <div class="form-check">
                        <a class="btn btn-info module" href="javascript:void(0)" role="button" data-check="true" onclick="f(this)">${module.name}</a>
                        <div></div>
                        <c:forEach items="${module.children}" var="m">
                            <c:if test="${ids.contains(m.id)}">
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input name="moduleId" class="form-check-input" type="checkbox" value="${m.id}" checked }> ${m.name}
                                    </label>
                                </div>
                            </c:if>
                            <c:if test="${not ids.contains(m.id)}">
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input name="moduleId" class="form-check-input" type="checkbox" value="${m.id}" }> ${m.name}
                                    </label>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
                    <%--<div class="form-check">--%>
                        <%--<a class="btn btn-info module" href="javascript:void(0)" role="button" data-check="true">一级权限</a>--%>
                        <%--<div></div>--%>
                            <%--<div class="form-check form-check-inline">--%>
                                <%--<label class="form-check-label">--%>
                                    <%--<input name="moduleId" class="form-check-input" type="checkbox" > 二级权限--%>
                                <%--</label>--%>
                            <%--</div>--%>
                    <%--</div>--%>
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
<script>
</script>
</body>
</html>