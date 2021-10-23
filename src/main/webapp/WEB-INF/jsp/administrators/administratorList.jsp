<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="administrators">
    <h2>Administrators</h2>

    <table id="administratorsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="administrator">
            <tr>
                <td>
                    <c:out value="${administrator.name}"/>
                </td>
                <td>
                    <c:forEach var="pet" items="${administrator.pets}">
                        <c:out value="${pet.name} "/>
                    </c:forEach>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${administrator.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${administrator.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
