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
            <c:url value="/admin/main" var="adminMainUrl"/>
            <div style="display: inline-block; margin-left: 15px;">
                <h4><a href="${adminMainUrl}">Admin area</a></h4>
            </div>
        </div>

        <div style="margin-top: 30px">
            <form:form method="POST" action="${pageContext.request.contextPath}/admin/addCategory"
                       modelAttribute="newCategory">
            <fieldset class="form-group">
                <form:label path="name" cssClass="col-sm-2 form-control-label">Category Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" required="required" cssClass="form-control"/>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <form:label path="parentId" cssClass="col-sm-2 form-control-label">Parent</form:label>
                <div class="col-sm-10">
                    <form:select id="parentId" path="parentId" cssClass="form-control">
                        <form:option value="" label="--- Select ---"/>
                        <form:options items="${parentlist}"/>
                    </form:select>
                </div>
            </fieldset>

            <fieldset class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </fieldset>
        </div>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
