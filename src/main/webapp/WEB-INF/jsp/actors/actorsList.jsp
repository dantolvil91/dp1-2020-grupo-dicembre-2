<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="peliculasOnline" tagdir="/WEB-INF/tags" %>

<peliculasOnline:layout pageName="actors">
    <h2>Actors</h2>

    <table id="actorsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Address</th>
            <th>City</th>
            <th style="width: 120px">Telephone</th>
            <th>Films</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="actor">
            <tr>
                <td>
                    <spring:url value="/actors/{actorId}" var="actorUrl">
                        <spring:param name="actorId" value="${actor.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(actorUrl)}"><c:out value="${actor.firstName} ${actor.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${actor.address}"/>
                </td>
                <td>
                    <c:out value="${actor.city}"/>
                </td>
                <td>
                    <c:out value="${actor.telephone}"/>
                </td>
                <td>
                    <c:forEach var="film" items="${actor.films}">
                        <c:out value="${film.name} "/>
                    </c:forEach>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${actor.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${actor.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</peliculasOnline:layout>
