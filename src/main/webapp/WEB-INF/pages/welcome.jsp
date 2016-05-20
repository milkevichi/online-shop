<%@include file="include.jsp" %>
<%@page session="true" %>
<html>
<head>
    <style>
        .img {
            width: 600px;
        }

        table {
            border-spacing: 20px;
        }

    </style>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <div class="jumbotron" style="padding-top: 1px; margin-top: -36px;">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="panel panel-default" style="width: 100%;">
                <c:url value="/admin/main" var="adminUrl"/>
                <h4><a style="margin-left: 15px;" href="${adminUrl}">Admin area</a></h4>
            </div>
        </sec:authorize>


        <div class="" style="display: block; text-align: right;">
            <sec:authorize access="!isAuthenticated()">
                <c:url value="/login" var="loginUrl"/>
                <a href="${loginUrl}">Sign In</a> |
                <c:url value="/signOn" var="signOnUrl"/>
                <a href="${signOnUrl}">Sign On</a>
            </sec:authorize>
        </div>


        <div class="form-group form-inline" style="width: 100%">
            <label for="searchTermId">Search: </label>
            <input type="text" style="width: 74%;" name="searchTerm" autocomplete="off"
                   class="form-control" id="searchTermId" placeholder="search">
            <input type="button" class="btn btn-default" onclick="doSearch()" value="Go"/>
        </div>


        <table>
            <tr>
                <td valign="top" width="30%">
                    <ul class="sf-menu sf-vertical sf-js-enabled sf-arrows" id="navigationMenu">
                        <c:url value="/welcome" var="categoriedProductsUrl"/>
                        <c:if test="${currentCategoryId != null}">
                            <li class="current">
                                <a href="${categoriedProductsUrl}" style="font-size: 12px; margin-left: -10px">
                                    <c:out value="< All categories"/>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach var="category" items="${rootCategories}">

                            <c:if test="${category.parent != null}">
                                <li class="current">
                                    <a style="font-size: 14px; margin-left: -10px"
                                       href="${categoriedProductsUrl}?categoryId=${category.parent.categoryId}">
                                        <c:out value="  < ${category.parent.name}"/>
                                    </a>
                                </li>
                            </c:if>
                            <li class="current">
                                <a href="${categoriedProductsUrl}?categoryId=${category.categoryId}">
                                    <c:out value="${category.name}"/>
                                </a>
                                <ul>
                                    <c:set var="category" value="${category}" scope="request"/>
                                    <jsp:include page="childCategory.jsp"/>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </td>

                <td width="70%">
                    <c:forEach var="product" items="${productsList}">
                        <div class="img">
                            <table width="100%" frame="border" class="table table-striped">
                                <tr>
                                    <td>
                                        <c:if test="${empty product.getImageBase64()}">
                                            <img src="${pageContext.request.contextPath}/resources/css/images/No_Image_Available.png"/>
                                        </c:if>
                                        <c:if test="${not empty product.getImageBase64()}">
                                            <img src="data:image;base64,${product.getImageBase64()}"/>
                                        </c:if>
                                    </td>
                                    <td valign="top">
                                        <c:out value="${product.name}"/>
                                    </td>
                                    <td valign="top">
                                        <c:out value="${product.price}"/>
                                        <br><br><br>
                                        <form:form method="POST" action=
                                                "${pageContext.request.contextPath}/order/addOrderUnit?from=welcome"
                                                   modelAttribute="orderUnit">
                                            <form:hidden path="productId" value="${product.id}"/>
                                            <form:hidden path="amount" value="${product.price}"/>
                                            <input type="submit" class="btn btn-default" value="Add to cart"/>
                                        </form:form>
                                        <br>
                                        <form:form method="POST" action=
                                                "${pageContext.request.contextPath}/order/buyNow"
                                                   modelAttribute="orderUnit">
                                            <form:hidden path="productId" value="${product.id}"/>
                                            <form:hidden path="amount" value="${product.price}"/>
                                            <input type="submit" class="btn btn-default" value="buyNow"/>
                                        </form:form>
                                    </td>
                                </tr>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <tr>
                                        <td>
                                            <c:url value="/admin/createProduct" var="createProductUrl"/>
                                            <a style="font-size: x-small"
                                               href="${createProductUrl}?productId=${product.id}">Edit product</a>
                                        </td>
                                    </tr>
                                </sec:authorize>
                            </table>

                        </div>

                    </c:forEach>
                </td>
            </tr>
        </table>

    </div>
</div>

<c:if test="${not empty modalMessage}">
    <div id="dialog" title="Product added" style="text-align: center">
        <p>${modalMessage}</p>
        <input type="button" class="btn btn-default" onclick="$('#dialog').dialog('close');" value="OK"/>
    </div>
</c:if>

<jsp:include page="footer.jsp"/>
</body>

</html>

<script>

    (function ($) { //create closure so we can safely use $ as alias for jQuery
        $(document).ready(function () {
            var navOpts = {
                speed: 'fast'
            };
            $('#navigationMenu').superfish(navOpts);

            <c:if test="${not empty modalMessage}">
            $("#dialog").dialog({
                height: 'auto', width: 'auto', modal: true,
                position: {my: "center top+150", at: "center top+150", of: window}
            });
            </c:if>

            $("#searchTermId").val(querySt('searchTerm'));
        });
    })(jQuery);

    $('#searchTermId').keydown(function(event) {
        if (event.keyCode == 13) {
            doSearch()
            return false;
        }
    });

    function doSearch() {
        var toUrl = '${pageContext.request.contextPath}/welcome?searchTerm=' + $("#searchTermId").val();

        var categoryId = querySt("categoryId");
        if (categoryId) {
            toUrl += ("&categoryId=" + categoryId);
        }

        window.location.href = toUrl;
    }
    function querySt(param) {

        var _url = window.location.search.substring(1);
        var params = _url.split("&");

        for (i = 0; i < params.length; i++) {
            rowParam = params[i].split("=");
            if (rowParam[0] == param) {
                return rowParam[1];
            }
        }
    }
</script>