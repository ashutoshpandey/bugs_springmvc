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
    @import url("<c:url value="/static/css/projects/edit.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/projects/edit.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="ys-adminform">

        <jsp:include page="../includes/header.jsp"/>

        <div style="margin-bottom: 20px;">
            <a href="${root}/list-projects">Project list</a>

            <br/><br/>
            <h1 class="form-header">Edit this project</h1>
        </div>

        <form id="form-update-project" onsubmit="return false">

            <div class="content">

                <div class="form-label">Project name</div>
                <div class="form-row">
                    <input id="name" name="name" class="input username" type="text" value="{{$project->name}}"/>
                </div>

                <div class="form-label">Description</div>
                <div class="form-row">
                    <textarea id="description" name="description" class="input" rows="10">{{$project->description}}</textarea>
                </div>

            </div>

            <div class="footerlogin">
                <input class="button" name="btn-update-project" value="Update" type="submit"/>

                <div class="message" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>