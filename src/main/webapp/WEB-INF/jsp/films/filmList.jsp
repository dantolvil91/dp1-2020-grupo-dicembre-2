<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="films">
    <h2>Films</h2>

    <table id="filmsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
         </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="film">
            <tr>
                <td>
                    <spring:url value="/films/{filmId}" var="filmUrl">
                        <spring:param name="filmId" value="${film.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(filmUrl)}"><c:out value="${film.firstName} ${film.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${film.name}"/>
                </td>             
      
<!--
                <td> 
                    <c:out value="${film.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${film.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
