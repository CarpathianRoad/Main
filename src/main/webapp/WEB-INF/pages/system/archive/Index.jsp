<%-- 
    Document   : Index
    Created on : Jun 26, 2015, 8:49:24 PM
    Author     : kiwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminpage>
    <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Category list
                        </h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        
                        <div class="list-group">
                            ${menuList}
                        </div>
                    </div>
                </div>
</t:adminpage>
<script>
    $(document).ready(function() {
        console.log("${sessionScope.user.user_name}");
        $( ".content-list a" ).each(function( index ) {
            var current = "<c:out value="${pageContext.session.id}"/>";
            $(this).attr("href", current);
        });
    });
</script>
