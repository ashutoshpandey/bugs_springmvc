<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>
        Administrator Section
    </title>

<style type="text/css">
	@import url("<c:url value="/static/css/jquery.dataTables.css"/>");
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/bugs/detail.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/bugs/detail.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">

        <jsp:include page="../includes/header.jsp"/>

        <div class="header">
            <div class="form-row">
                <a href="${root}/list-bugs/${project.id}">Back</a>
                <br/>
            </div>
            <br/>

            <div class="form-row">
                Project: <b>${project.name}</b><br/>
            </div>

            <div class="form-row">
                Posted by : <span style="color:#478bff; font-weight: 700">${bug.user.name}</span> on <b>( <fmt:formatDate type="both" value="${bug.createdAt}" timeStyle="short" /> )</b>
            </div>
            <div class="form-row">
                Level : <span class="${fn:toLowerCase(bug.severity)}">${bug.severity}</span>
            </div>
            <br/>
            <div class="form-row">
                <strong>${bug.title}</strong>
            </div>
            <br/>
            <div class="form-row">
                ${bug.description}
            </div>
            <br/>
            
                <c:if test="${bugFiles ne null && fn:length(bugFiles)>0}">

					<c:forEach var="bugFile" items="${bugFiles}">

                        <!-- $extension = pathinfo($bugFile->file_name, PATHINFO_EXTENSION); -->

                        <div class='form-row'>

							<a href='${root}/download-bug/${bug.id}'>${bugFile.fileName}</a>
<!-- 
                        if(in_array($extension, $image_types))
                            <a href='${root}/public/uploads/${bugFile.savedFileName}' target='_blank'><img class='bug-image' src='${root}/public/uploads/${bugFile.savedFileName}'/></a>
                        else
                            <a href='${root}/download-bug/$bug->id'>${bugFile.fileName}</a>
-->
                        </div>
                    
                    </c:forEach>
                    
                </c:if>

            <br/>
            <div class="form-row">
                <form action="${root}/save-bug-comment" id="form-comment" method="post" target="ifr" enctype="multipart/form-data" onsubmit="return checkComment()">
                    <textarea name="comment" rows="5" cols="40" placeholder="Add your comment"></textarea>

                    <br/><br/>

                    <div class="form-row"><span class="add-file">Add attachment</span></div>

                    <br/>

                    <div class="form-row file-container"></div>
                    <br/>

                    <input type="submit" name="btn-add-comment" value="Add comment"/>
                </form>
                <iframe id="ifr" name="ifr" style="width:1px;height:1px;visibility: hidden"></iframe>
            </div>

            <div class="form-row bug-comments"></div>

        </div>

        <div id="bug-comments"></div>
    </div>

</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>