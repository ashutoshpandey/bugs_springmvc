<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>
        Administrator Section
    </title>

<style type="text/css">
    @import url("<c:url value="/static/css/jquery.dataTables.css"/>");
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/bugs/list.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/list.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <div class="header">
            <div>
                <a href="${root}/create-bug">Create bug</a>
            </div>
            <br/>
            <h1 class="form-header">Listing bugs</h1>

            <label><input type="radio" name="bug_type" value="active" checked="checked"/>Active </label> &nbsp;&nbsp;
            <label><input type="radio" name="bug_type" value="fixed"/>Fixed </label> &nbsp;&nbsp;
            <label><input type="radio" name="bug_type" value="unresolved"/>Unresolved </label>

            <br/><br/><br/>
        </div>

        <div id="table-data"></div>
    </div>

</div>

<jsp:include page="../includes/footer.jsp"/>
<jsp:include page="../includes/popup.jsp"/>

</body>
</html>