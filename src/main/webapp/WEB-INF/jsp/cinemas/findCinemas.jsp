<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="cinemas">

    <h2>Find Cinemas</h2>

    
    <form:form modelAttribute="cinema" action="/cinemas" method="get" class="form-horizontal"
               id="search-cinema-form">
        <div class="form-group">
            <div class="control-group" id="lastName">
                <label class="col-sm-2 control-label">Last name </label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="lastName" size="30" maxlength="80"/>
                    <span class="help-inline"><form:errors path="*"/></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Find Cinema</button>
            </div>
        </div>

    </form:form>

    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/cinemas/new" htmlEscape="true"/>'>Add Cinema</a>
	</sec:authorize>
	
</petclinic:layout>
