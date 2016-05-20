<%@include file="include.jsp" %>
<%@page session="true" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        #login-box {
            width: 500px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<jsp:include page="header.jsp"/>
<div class="container">
    <div class="jumbotron" style="padding-top: 1px; margin-top: -36px;">
        <div class="panel panel-default">
            <c:url value="/welcome" var="welcomeUrl"/>
            <div style="display: inline-block; margin-left: 15px;">
                <h4><a href="${welcomeUrl}">Return to main page</a></h4>
            </div>
        </div>
        <h3>Spring Security Login Form (Database Authentication)</h3>

        <div id="login-box">

            <h4>Login with Username and Password</h4>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <c:url value="/j_spring_security_check" var="loginUrl"/>
            <form name='loginForm' class="form-horizontal"
                  action="${loginUrl}" method='POST'>

                <div class="form-group">
                    <label class="col-sm-2 control-label" for="username">User:</label>

                    <div class="col-sm-10">
                        <input class="form-control" type='text' id="username" name='username'>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="password">Password:</label>

                    <div class="col-sm-10">
                        <input class="form-control" id="password" type='password' name='password'/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>