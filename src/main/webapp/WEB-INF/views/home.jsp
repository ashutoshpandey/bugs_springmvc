<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>
        Administrator Section
    </title>

<style type="text/css" media="screen">
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/login.css"/>");
</style>

	<jsp:include page="includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/login.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="ys-adminform">

        <jsp:include page="includes/header_logo.jsp"/>

        <form id="form-login" class="admin-section-form frm">

            <div class="header">
                <br/>

                <h1>Login</h1>
                <br/>
            </div>

            <div class="content">

                <div class="form-row">
                    <input name="email" class="input" placeholder="Email" type="text"/>

                    <div class="user-icon"></div>
                </div>
                <div class="form-row">
                    <input name="password" class="input password" placeholder="Password" type="password"/>

                    <div class="pass-icon"></div>
                </div>
            </div>

            <div class="footerlogin">
                <input class="button" name="btn-login" value="Authenticate" type="button"/>

                <div class="message" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>

    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
</body>
</html>