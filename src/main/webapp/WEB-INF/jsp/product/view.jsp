<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>

<div class="container">
  <!-- Static navbar -->
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/agroprom/">Agroprom</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="">Товары</a></li>
        <li><a href="">Продавцы</a></li>
        <li><a href="">Производители</a></li>
      </ul>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="">Вход</a></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </nav>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
      <form:form id="product_form" action="product/add" method="POST" commandName="product" acceptCharset="UTF-8" htmlEscape="true">
        <div class="row">
          <div class="col-md-9">
            <div class="form-group">
              <label for="name">Название</label>
              <form:input path="name" cssClass="form-control"/>
            </div>
          </div>
          <div class="col-md-3">
            <div class="form-group">
              <label for="price">Цена</label>
              <form:input path="price" cssClass="form-control"/>
            </div>
          </div>
        </div>

        <div class="row">
          <%--LAMBADA--%>
          <div class="col-md-4">
            <form:select path="category.id" items="${categories}"/>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label for="descriptionMarkdown">Описание</label>
              <form:textarea path="descriptionMarkdown" rows="5" cssClass="form-control"/>
            </div>
            <button type="submit" class="btn btn-default">Добавить</button>
          </div>
        </div>
      </form:form>
    </div>
  </div>
  <div class="row" id="product_list">
    <div class="col-md-1"></div>
    <div class="col-md-10">
      <table class="table">
        <thead>
        <th>id</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Категория</th>
        <th></th>
        <th></th>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
          <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
            <td>
              <button class="btn btn-default btn-xs">
                <span class="glyphicon glyphicon-edit"></span>
              </button>
            </td>
            <td>
              <button id="delete_button" class="btn btn-default btn-xs" value="${product.id}">
                <span class="glyphicon glyphicon-remove"></span>
              </button>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/product.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>