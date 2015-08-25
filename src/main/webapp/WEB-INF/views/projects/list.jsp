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
    @import url("<c:url value="/static/css/projects/list.css"/>");
</style>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/projects/list.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <div class="header">
            <div>
                <a href="{{$root}}/create-project">Create project</a>
            </div>

            <br/>
            <h1 class="form-header">Project list</h1>
            <br/>
        </div>

        <div id="table-data"></div>
    </div>

</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>