<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <c:if test="${user != null}">
    <a class="navbar-brand" href="javascript:void(0)">${user.username}</a>
  </c:if>
  <c:if test="${user == null}">
    <a class="navbar-brand" href="/login.jsp">请登录</a>
  </c:if>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/index.jsp">首页</a>
      </li>
        <c:forEach items="${allModules}" var="module">
            <c:if test="${ parentModules.contains(module.id)}">
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${module.name}</a>
                <div class="dropdown-menu" aria-labelledby="dropdown1">
                <c:forEach items="${module.children}" var="m">
                  <c:if test="${userModules.contains(m.id)}">
                    <a class="dropdown-item" href="${m.code}">${m.name}</a>
                  </c:if>
                </c:forEach>
                </div>
                </li>
        </c:if>
        </c:forEach>

      <%--<li class="nav-item dropdown">--%>
        <%--<a class="nav-link dropdown-toggle" href="javascript:void(0)" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">系统管理</a>--%>
        <%--<div class="dropdown-menu" aria-labelledby="dropdown1">--%>
          <%--<a class="dropdown-item" href="/system/email">邮件设置</a>--%>
          <%--<a class="dropdown-item" href="/system/sms">短信设置</a>--%>
          <%--<a class="dropdown-item" href="/system/user/page">用户管理</a>--%>
          <%--<a class="dropdown-item" href="/system/role/all">权限分配</a>--%>
        <%--</div>--%>
      <%--</li>--%>
      <%--<li class="nav-item dropdown">--%>
        <%--<a class="nav-link dropdown-toggle" href="javascript:void(0)" id="dropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">订单管理</a>--%>
        <%--<div class="dropdown-menu" aria-labelledby="dropdown2">--%>
          <%--<a class="dropdown-item" href="/order/search">查询订单</a>--%>
          <%--<a class="dropdown-item" href="/order/refund">退单处理</a>--%>
          <%--<a class="dropdown-item" href="/order/stat">统计分析</a>--%>
        <%--</div>--%>
      <%--</li>--%>
      <%--<li class="nav-item">--%>
        <%--<a class="nav-link disabled" href="javascript:void(0)">商品管理</a>--%>
      <%--</li>--%>


      <li class="nav-item">
        <form id="logout" action="/logout" method="post">
          <a class="nav-link" href="javascript:document.getElementById('logout').submit()">注销</a>
        </form>
      </li>        
    </ul>
    <form class="form-inline mt-2 mt-md-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>



</nav>