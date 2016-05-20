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
            <form:form method="POST"
                       action="${pageContext.request.contextPath}/admin/addProduct?${_csrf.parameterName}=${_csrf.token}"
                       modelAttribute="product" enctype="multipart/form-data">
            <form:hidden path="id"/>
                <fieldset class="form-group">
                    <form:label path="name" cssClass="col-sm-2 form-control-label">Product Name</form:label>
                    <div class="col-sm-10">
                        <form:input required="required" path="name" cssClass="form-control"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="category" cssClass="col-sm-2 form-control-label">Category</form:label>
                    <div class="col-sm-10">
                        <form:select required="required" id="categoryId" path="categoryId"  cssClass="form-control">
                            <form:option value="" label="--- Select ---"/>
                            <form:options items="${categorylist}"/>
                        </form:select>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="price" cssClass="col-sm-2 form-control-label">Price</form:label>
                    <div class="col-sm-10">
                        <form:input path="price" pattern="\d+(\.\d{1,2})?" placeholder="00.00"
                                    required="required" cssClass="form-control"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <form:label path="image" cssClass="col-sm-2 form-control-label">Image</form:label>
                    <div class="col-sm-10">
                        <label class="file">
                            <input type="file" name="file" id="file" accept="image/*"/>
                            <span class="file-custom"></span>
                        </label>
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
</body>
</html>
