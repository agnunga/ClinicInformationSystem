<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="assets/header.jsp"/>
<div style="margin: 20%">
    <h3 style="text-align: left;">
        <%! int a = 191;%>
        <% int b = 657;%>
        <c:set var="number" value="131" scope="application"></c:set>
        ${number}<br/>
        Imported<br/>
        <c:import url="another.jsp"></c:import>
        <c:set var="age" value="19"></c:set><br/>
            The if age thing<br/>
        <c:if test="${age>8}">
            <c:out value="You are above 18. ."></c:out>
        </c:if>
        The choose age thing<br/>
        <c:choose>
            <c:when test="${age>10&&age<20}">
                <c:out value="Age between 10 and 20"></c:out>
            </c:when>
            <c:when test="${age<10&&age>0}">  
                <c:out value="Age is less than 10"></c:out>
            </c:when>
            <c:otherwise>
                <c:out value="Age out of range"></c:out>
            </c:otherwise>
        </c:choose>
        <br/>The begin JSTL<br/>
        <c:forEach var="counter" begin="0" end="10">
            <c:out value="${counter}"></c:out>
        </c:forEach>
        <br/>The forEach token<br/>
        <c:forTokens items="www.systech.co.ke" delims="." var="names">
            ${names} &nbsp;
        </c:forTokens>
        <%! String[] names = {"Oloo", "Jade", "Palase", "Kuta", "Sisilo"};%>
        <c:forTokens items="names[]" delims="," var="names">
            ${names}
        </c:forTokens>
    </h3>
</div>
<%@include file="assets/footer.jsp"%>