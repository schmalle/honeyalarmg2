
<%@ page import="honeyalarmg2.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-report" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="attacker" title="${message(code: 'report.attacker.label', default: 'Attacker')}" />
					
						<g:sortableColumn property="request" title="${message(code: 'report.request.label', default: 'Request')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'report.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'report.time.label', default: 'Time')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'report.type.label', default: 'Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reportInstanceList}" status="i" var="reportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reportInstance.id}">${fieldValue(bean: reportInstance, field: "attacker")}</g:link></td>
					
						<td>${fieldValue(bean: reportInstance, field: "request")}</td>
					
						<td>${fieldValue(bean: reportInstance, field: "status")}</td>
					
						<td>${fieldValue(bean: reportInstance, field: "time")}</td>
					
						<td>${fieldValue(bean: reportInstance, field: "type")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reportInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
