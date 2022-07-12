<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMV Samples</title>
        <jsp:include page="head.jsp" />
    </head>
    <body>

        <jsp:include page="top_menu.jsp" />

        <div class="container">
            <div class="px-4 pt-5 my-5 text-center border-bottom">
                <h1 class="display-4 fw-bold">Wellcome to AMV samples!</h1>
                <div class="col-sm-6 mx-auto">
                    <p class="lead mb-4">placeholder</p>
                </div>
            </div>

            <div>
                <c:forEach var="row" items="${rows}">
                    <div class="row">
                        <c:forEach var="group" items="${row.get()}">
                            <div class="col-sm-2">
                                <div class="card" style="margin: 5px 0">
                                    <img class="card-img-top" loading="lazy"
                                         src="${pageContext.request.contextPath}/poster/${group}">
                                    <div class="card-body text-center">
                                        <div>
                                            <span class="card-text">
                                                ${group.replaceAll("_", " ")}
                                            </span>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/group/${group}" class="btn btn-primary btn-sm">view</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                </c:forEach>

            </div>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
