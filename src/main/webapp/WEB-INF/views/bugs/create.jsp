<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    @import url("<c:url value="/static/css/bugs/create.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/create.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <form id="form-bug" method="post" action="${root}/save-bug" target="ifr" name="ifr" enctype="multipart/form-data">

            <div class="header">
                <div>
                    <a href="${root}/list-bugs/${projectId}">View bugs</a> <br/>
                </div>

                <br/>
                <h1 class="form-header">Create bug</h1>
            </div>

            <div class="content">
                <div class="form-label">Title</div>
                <div class="form-row">
                    <input name="title" class="input" type="text"/>
                </div>

                <div class="form-label">Description</div>
                <div class="form-row">
                    <textarea name="description" class="input" rows="5"></textarea>
                </div>

                <div class="form-label">Bug Severity</div>
                <div class="form-row">
                    <select name="severity">
                        <option>Blocker</option>
                        <option>Critical</option>
                        <option>Major</option>
                        <option>Minor</option>
                        <option>Warning</option>
                    </select>
                </div>

                <c:if test="${users ne null && fn:length(users)>0}">
                
                <div class="form-label">Assign to</div>
                <div class="form-row">
                    <select name="users" multiple="multiple" style="min-height: 50px; max-width: 300px">
                    
                    <c:forEach var="user" items="${users}">
						<option value='${user.id}'>${user.name}</option>
					</c:forEach>                        
                        
                    </select>
                </div>
                
                </c:if>

                <br/>

                <div class="form-row"><span class="add-file">Add attachment</span></div>

                <br/>

                <div class="form-row file-container"></div>
            </div>

            <div class="footerlogin">
                <input class="button" name="btn-create-bug" value="Create" type="submit"/>

                <div class="message" style="font-weight: bold; padding-top:16px;">&nbsp;</div>
            </div>

        </form>
        <iframe id="ifr" name="ifr" style="width:1px;height:1px;visibility: hidden"></iframe>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>