<%@include file="include.jsp" %>
<%@page session="true" %>

<html>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="jumbotron" style="padding-top: 1px; margin-top: -36px;">
        <div class="panel panel-default">
            <c:url value="/admin/createCategory" var="createCategoryUrl"/>
            <c:url value="/admin/createProduct" var="createProductUrl"/>
            <c:url value="/welcome" var="welcomeUrl"/>

            <div style="display: inline-block; margin-left: 15px;">
                <h4><a href="${createCategoryUrl}">Create category</a> |
                    <a href="${createProductUrl}">Create product</a> |
                    <a href="${welcomeUrl}">Return to main page</a>
                </h4>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>