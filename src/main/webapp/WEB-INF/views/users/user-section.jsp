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
    @import url("<c:url value="/static/css/jquery.dataTables.css"/>");
    @import url("<c:url value="/static/css/common.css"/>");
    @import url("<c:url value="/static/css/theme/transdmin.css"/>");
    @import url("<c:url value="/static/css/users/user-section.css"/>");
</style>

	<jsp:include page="../includes/common.jsp"/>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/users/user-section.js"/>"></script>

</head>
<body>
<div>

    <div id="wrapper" class="main-container">
    
        <jsp:include page="../includes/header.jsp"/>

        <table>
            <tr>
                <td style="width: 150px; line-height: 30px;">Running projects</td>
                <td style="width:50px">:</td>
                <td>${runningProjects}</td>
            </tr>
            <tr>
                <td style="width: 150px; line-height: 30px">Closed projects</td>
                <td style="width:50px">:</td>
                <td>${closedProjects}</td>
            </tr>
            <tr>
                <td style="width: 150px; line-height: 30px" colspan="3"><hr/></td>
            </tr>
            <tr>
                <td style="width: 150px; line-height: 30px">Current bugs count</td>
                <td style="width:50px">:</td>
                <td>${currentBugs}</td>
            </tr>
            <tr>
                <td style="width: 150px; line-height: 30px">Fixed bugs count</td>
                <td style="width:50px">:</td>
                <td>${fixedBugs}</td>
            </tr>
            <tr>
                <td style="width: 150px; line-height: 30px">Unresolved bugs</td>
                <td style="width:50px">:</td>
                <td>${unresolvedBugs}</td>
            </tr>
        </table>

		<c:if test="${userBugs ne null && fn:length(userBugs)>0}">
        
                <br/><br/><b>Bugs you are assigned on</b><br/><br/>

                <c:set var="i" value="0"/>
                
                <c:forEach var="userBug" items="${userBugs}">

                    <c:set var="i" value="${i+1}"/>

                    <c:set var="project" value="${userBug.bug.project}"/>

                    <b>${i}.</b> <u>${project.name}</u> : <a href='${root}/bug-detail/${userBug.bug_id}'>${userBug.bug.title}</a><br/><br/>";
                </c:forEach>
        </c:if>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>