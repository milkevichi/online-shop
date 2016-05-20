<%@include file="include.jsp" %>

<html>
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

        <c:choose>
            <c:when test="${empty userCartItems}">
                No products
            </c:when>
            <c:otherwise>
                <c:set var="summ" value="${0}"/>
                <h3>Order details: </h3>
                <table class="table table-striped" style="text-align: center">
                    <thead>
                    <tr>
                        <td></td>
                        <td>Name</td>
                        <td>Count</td>
                        <td>Price</td>
                        <td>Amount</td>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${userCartItems}">
                        <tr>
                            <td><img style="max-width: 100px; max-height: 100px"
                                     src="data:image;base64,${order.product.getImageBase64()}"/></td>
                            <td>${order.product.name}</td>
                            <td>
                                <div style="margin-bottom: -10px">
                                    <form:form method="POST" action=
                                            "${pageContext.request.contextPath}/order/addOrderUnit?from=order/cart"
                                               modelAttribute="orderUnit">
                                        <form:hidden path="productId" value="${order.product.id}"/>
                                        <form:hidden path="amount" value="${order.product.price}"/>
                                        <form:hidden path="count" value="${-1}"/>
                                        <input type="submit" class="btn btn-default" value="-"/>
                                    </form:form>
                                </div>
                                    ${order.count}
                                <div>
                                    <form:form method="POST" action=
                                            "${pageContext.request.contextPath}/order/addOrderUnit?from=order/cart"
                                               modelAttribute="orderUnit">
                                        <form:hidden path="productId" value="${order.product.id}"/>
                                        <form:hidden path="amount" value="${order.product.price}"/>
                                        <form:hidden path="count" value="${1}"/>
                                        <input type="submit" class="btn btn-default" value="+"/>
                                    </form:form>
                                </div>

                            </td>
                            <td>${order.product.price}</td>
                            <td>${order.product.price * order.count}</td>
                            <c:set var="summ" value="${summ + order.product.price * order.count}"/>
                        </tr>
                    </c:forEach>
                </table>
                <div style="display: block; text-align: right">
                    <h3>Total: <c:out value="${summ}"/></h3>
                    <form:form method="POST" action="${pageContext.request.contextPath}/order/checkout">
                        <input type="submit" class="btn btn-default" value="Checkout"/>
                    </form:form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>