<%@page import="java.util.Date"%>
<%@include file="assets/header.jsp"%>
<div style="margin: 20%">
    <h1 style="text-align: center;">
        <jsp:useBean id="u" class="com.trials.beans.UserBean"/>
        <jsp:setProperty property="*" name="u"/>
        
        Details:<br/>
        <jsp:getProperty name="u" property="name"/> <br/>
        <jsp:getProperty name="u" property="email"/> <br/>
        <jsp:getProperty name="u" property="password"/> <br/>
        <%= "Time now: " + new Date()%><br/>
        <% int a = Integer.parseInt("90");%><br/>
        <%= "Division: " + a / 3%><br/>
        <%--<jsp:forward page="another.jsp">--%>
        <%--<jsp:param name="name" value="Oloo Jade Palase"/>--%>
        <%--</jsp:forward>--%>
        <jsp:include page="another.jsp">
            <jsp:param name="name" value="Oloo Jade Palase"/>
        </jsp:include>

        <jsp:useBean id="c" class="com.trials.beans.TryBean"/>
        <% double cube = c.cube(32);%>
        <%= "The cude of 32 is: " + cube%>
    </h1>
</div>
<%@include file="assets/footer.jsp"%>