<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>
        Administrator Section
    </title>

<style type="text/css">
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/bugs/edit.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/edit.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="ys-adminform">

        <jsp:include page="../includes/header.jsp"/>

        <form name="admin-section-form" class="admin-section-form frm">

            <div class="header">
                <div style="">
                    <img src="images/logo.png" style="" alt=""/>
                </div>
                <br/>
                <h1 class="form-header">Edit bug</h1>
            </div>

            <div class="content">
                <input name="username" class="input username" placeholder="Username" type="text"/>

                <div class="user-icon"></div>
                <input name="password" class="input password" placeholder="Password" type="password"/>

                <div class="pass-icon"></div>
            </div>

            <div class="footerlogin">
                <input class="button" name="btnlogin" value="Authenticate" type="button"/>

                <div class="msgtask" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
</body>
</html>