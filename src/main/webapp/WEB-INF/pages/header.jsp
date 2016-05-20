<%@include file="include.jsp" %>
<%@page session="true" %>
<div class="container">
    <div class="jumbotron"  style="padding-bottom: 1px;">
        <div class="page-header" style="text-align: center">
            <%--<c:if test="${not empty title}">--%>
                <h2 class="bg-primary">Milkevich test shop</h2>
            <%--</c:if>--%>

            <%--<c:if test="${not empty message}">--%>
                <h3 class="bg-info">buy your dream!</h3>
            <%--</c:if>--%>
        </div>

        <sec:authorize access="hasRole('ROLE_USER')">
            <!-- For login user -->
            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <sec:authentication property="principal.authorities" var="authorities"/>

                <div class="panel panel-default">
                    <div style=" display: inline-block; ">
                        <h4 style="margin-left: 15px;"> Granted roles:
                            <c:forEach items="${authorities}" var="authority">
                                ${authority.authority}
                            </c:forEach>
                        </h4>
                    </div>
                    <div style="float: right; display: inline-block; margin-right: 15px;">
                        <c:set var="userCartSize" value="0"/>
                        <c:set var="userCartItems" value="${sessionScope.get('userCartItems')}"/>
                        <c:if test="${userCartItems != null}">
                            <c:set var="userCartSize" value="${fn:length(userCartItems)}"/>
                        </c:if>
                        <h4><a href="${pageContext.request.contextPath}/order/cart" style="margin-right: 55px">Cart(${userCartSize})</a> User : ${pageContext.request.userPrincipal.name} |
                            <a href="javascript:formSubmit()"> Logout </a></h4>
                    </div>
                </div>
            </c:if>
        </sec:authorize>
    </div>
</div>