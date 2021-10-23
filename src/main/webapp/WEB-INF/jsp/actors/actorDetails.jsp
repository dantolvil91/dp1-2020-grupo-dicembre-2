<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="peliculasOnline" tagdir="/WEB-INF/tags" %>

<filmclinic:layout pageName="actors">

    <h2>Actor Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${actor.firstName} ${actor.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${actor.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${actor.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${actor.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{actorId}/edit" var="editUrl">
        <spring:param name="actorId" value="${actor.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Actor</a>

    <spring:url value="{actorId}/films/new" var="addUrl">
        <spring:param name="actorId" value="${actor.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Film</a>

    <br/>
    <br/>
    <br/>
    <h2>Films and Visits</h2>

    <table class="table table-striped">
        <c:forEach var="film" items="${actor.films}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${film.name}"/></dd>
                        <dt>Birth Date</dt>
                        <dd><filmclinic:localDate date="${film.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${film.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Visit Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${film.visits}">
                            <tr>
                                <td><filmclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/actors/{actorId}/films/{filmId}/edit" var="filmUrl">
                                    <spring:param name="actorId" value="${actor.id}"/>
                                    <spring:param name="filmId" value="${film.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(filmUrl)}">Edit film</a>
                            </td>
                            <td>
                                <spring:url value="/actors/{actorId}/films/{filmId}/visits/new" var="visitUrl">
                                    <spring:param name="actorId" value="${actor.id}"/>
                                    <spring:param name="filmId" value="${film.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Add Visit</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</filmclinic:layout>
