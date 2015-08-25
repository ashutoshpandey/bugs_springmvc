<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>
        Administrator Section
    </title>

<style type="text/css">
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/users/create.css"/>");
</style>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/users/create.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <form id="form-create-user" class="admin-section-form frm" onsubmit="return false">

            <div class="header">
                <div>
                    <a href="{{$root}}/list-users">View users</a> <br/>
                </div>

                <br/>
                <h1 class="form-header">Create a new user</h1>
                <br/>
            </div>

            <div class="content">

                <div class="form-row">
                    <input id="email" name="email" class="input" placeholder="Email" type="text"/>
                </div>

                <div class="form-row">
                    <input id="name" name="name" class="input" placeholder="Name" type="text"/>
                </div>

                <div class="form-row">
                    <input id="password" name="password" class="input" placeholder="Password" type="password"/>
                </div>

                <div class="form-row">
                    <input id="confirm_password" name="confirm_password" class="input" placeholder="Confirm password" type="password"/>
                </div>

                <div class="form-row">
                    <select name="user_type">
                        <option>Administrator</option>
                        <option>Guest</option>
                        <option>User</option>
                    </select>
                </div>
            </div>

            <div class="footerlogin">
                <input class="button" name="btn-create-user" value="Create" type="submit"/>

                <div class="message" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>