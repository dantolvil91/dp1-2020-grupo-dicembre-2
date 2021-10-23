<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="administrators">
    <h2>
        <c:if test="${administrator['new']}">New </c:if> Administrator
    </h2>
    <form:form modelAttribute="administrator" class="form-horizontal" id="add-administrator-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${administrator['new']}">
                        <button class="btn btn-default" type="submit">Add administrator</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update administrator</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
