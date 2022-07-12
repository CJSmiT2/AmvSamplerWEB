<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${group.getClearName()} (AMV Samples)</title>
        <jsp:include page="head.jsp" />
    </head>
    <body>

        <jsp:include page="top_menu.jsp" />

        <br>
        <div class="container">
            <h2>${group.getClearName()}</h2>
            <c:forEach var="sample" items="${group.getSamples()}">
                <c:if test="${sample.isNotEmpty()}">
                    <a href="${pageContext.request.contextPath}/mp4/${group.getName()}/${sample.getFragment()}">
                        <img class="img-thumbnail" 
                             style="margin: 2px" 
                             loading="lazy"
                             src="${pageContext.request.contextPath}/gif/${group.getName()}/${sample.getFragment()}">
                    </a>
                </c:if>
            </c:forEach>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
