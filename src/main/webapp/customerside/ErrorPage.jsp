<%@page import="com.narola.hotelbooking.Utility.UserURLConstant" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.narola.hotelbooking.Utility.Constant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <jsp:include page="layouts/stylesheets.jsp">
        <jsp:param value="Error Page" name="title"/>
    </jsp:include>
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="admin/layouts/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <style>
        .card-horizontal {
            display: flex;
            flex: 1 1 auto;
        }

        .mylable {
            display: block;
            font-weight: bold;
            margin-bottom: 2px;
        }
    </style>
</head>
<body>
<jsp:include page="layouts/topnav.jsp"></jsp:include>
<br>
<div style="width: 100%; height: 120px; background: #EE5057"></div>
<br><br>

<!-- start slider section -->
<div class="slider_section">
    <div align="center">
        <H1>ERROR</H1>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xl-12 centered container">
                <div class="card" style="padding: 15px;">
                    <%--                    <div class="card-horizontal">--%>
                    <div class="card-body">
                        <%
                            String errMsg = (String) request.getAttribute("errMsg");
                            if (errMsg != null && !errMsg.trim().isEmpty()) {
                        %>
                        <h4><span style="color:red"><li><%=errMsg%></li></span></h4>
                        <%
                            }
                        %>
                    </div>
                    <%--                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br>
<jsp:include page="layouts/footer.jsp"></jsp:include>

<jsp:include page="layouts/scripts.jsp"></jsp:include>

</body>
</html>