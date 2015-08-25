<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<img src="<c:url value="/static/images/logo.png"/>"/>

<h1>Bug Tracking System</h1>

Welcome : ${name} <br/><br/><br/>

<!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
<ul id="mainNav">
    <li><a href="<c:url value="/user-section"/>">DASHBOARD</a></li>
    <li><a href="<c:url value="/list-projects"/>">PROJECTS</a></li>
    
    <c:if test='${ userType eq "Administrator" }'>
        <li><a href="<c:url value="/list-users"/>">USERS</a></li>
    </c:if>
    <li><a href="<c:url value="/profile"/>">PROFILE</a></li>
    <li class="logout"><a href="<c:url value="/logout"/>">LOGOUT</a></li>
</ul>