<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/welcome" var="categoriedProductsUrl"/>
<c:forEach var="category" items="${category.children}">
  <li class="current">
    <a href="${categoriedProductsUrl}?categoryId=${category.categoryId}">
      <c:out value="${category.name}"/>
    </a>
    <c:if test="${not empty category.children}">
      <ul>
        <c:set var="category" value="${category}" scope="request"/>
        <c:import url="childCategory.jsp"/>
      </ul>
    </c:if>
  </li>
</c:forEach>