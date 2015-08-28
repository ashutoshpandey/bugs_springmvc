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
    @import url("<c:url value="/static/css/users/edit.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/users/edit.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <form id="form-edit-user" class="admin-section-form frm" onsubmit="return false">

            <div class="header">
                <div>
                    <a href="${root}/list-users">View users</a> <br/>
                </div>

                <br/>
                <h1 class="form-header">Update this user</h1>
                <br/>
            </div>

            <div class="content">

                <div class="form-row">
                    ${user.email}
                </div>

                <div class="form-row">
                    <input id="name" name="name" class="input" placeholder="Name" type="text" value="${user.name}"/>
                </div>

                <div class="form-row">
                    <input id="password" name="password" class="input" placeholder="Password" type="password" value="${user.password}"/>
                </div>

                <div class="form-row">
                    <input id="confirm_password" name="confirm_password" class="input" placeholder="Confirm password" type="password" value="${user.password}"/>
                </div>

                <div class="form-row">
                    <select name="user_type">
                        <option>Administrator</option>
                        <option>Guest</option>
                        <option>User</option>
                    </select>
                    <script type="text/javascript">
                        $("select[name='user_type']").val("${user.userType}");
                    </script>
                </div>
            </div>

            <div class="footerlogin">
                <input class="button" name="btn-update-profile" value="Update" type="submit"/>

                <div class="message" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>