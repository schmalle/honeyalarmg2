
<%@ page import="honeyalarmg2.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-report" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list report">
			
				<g:if test="${reportInstance?.attacker}">
				<li class="fieldcontain">
					<span id="attacker-label" class="property-label"><g:message code="report.attacker.label" default="Attacker" /></span>
					
						<span class="property-value" aria-labelledby="attacker-label"><g:fieldValue bean="${reportInstance}" field="attacker"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.request}">
				<li class="fieldcontain">
					<span id="request-label" class="property-label"><g:message code="report.request.label" default="Request" /></span>
					
						<span class="property-value" aria-labelledby="request-label"><g:fieldValue bean="${reportInstance}" field="request"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="report.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${reportInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="report.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${reportInstance}" field="time"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="report.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${reportInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:reportInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${reportInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
