<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:include page="layouts/stylesheets.jsp">
        <jsp:param value="Error Page" name="title"/>
    </jsp:include>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

    <jsp:include page="layouts/topnav.jsp"></jsp:include>

    <jsp:include page="layouts/sidemenu.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-12">
                        <h1 class="m-0">ERROR</h1>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">

                <%
                    String errMsg = (String) request.getAttribute("errMsg");

                    if (errMsg != null && !errMsg.trim().isEmpty()) {
                %>
                <h3><span style="color:red"> * <%=errMsg%></span></h3>
                <%
                    }
                %>
            </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="layouts/footer.jsp"></jsp:include>

</div>
<!-- ./wrapper -->

<jsp:include page="layouts/scripts.jsp"></jsp:include>

</body>
</html>


