<%@include file="include.jsp" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <div class="jumbotron" style="padding-top: 1px; margin-top: -36px;">
        <div class="panel panel-default">
            <c:url value="/welcome" var="welcomeUrl"/>
            <div style="display: inline-block; margin-left: 15px;">
                <h4><a href="${welcomeUrl}">Return to main page</a></h4>
            </div>
        </div>
        <h3>Sign On Form</h3>
        <form:form method="POST" cssClass="form-horizontal" action="${pageContext.request.contextPath}/addUser"
                   modelAttribute="newUser">
            <div class="form-group">
                <form:label cssClass="col-sm-2 control-label" path="username">User Name</form:label>
                <div class="col-sm-10">
                    <form:input path="username"/>
                </div>
            </div>
            <div class="form-group">
                <form:label cssClass="col-sm-2 control-label" path="password">Password</form:label>
                <div class="col-sm-10">
                    <form:password path="password"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign on</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
