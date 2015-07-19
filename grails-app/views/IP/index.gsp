
<%@ page import="honeyalarmg2.IP" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'IP.label', default: 'IP')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-IP" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-IP" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="text" title="${message(code: 'IP.text.label', default: 'Text')}" />
					
						<g:sortableColumn property="firstSeen" title="${message(code: 'IP.firstSeen.label', default: 'First Seen')}" />
					
						<g:sortableColumn property="lastSeen" title="${message(code: 'IP.lastSeen.label', default: 'Last Seen')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${IPInstanceList}" status="i" var="IPInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${IPInstance.id}">${fieldValue(bean: IPInstance, field: "text")}</g:link></td>
					
						<td>${fieldValue(bean: IPInstance, field: "firstSeen")}</td>
					
						<td>${fieldValue(bean: IPInstance, field: "lastSeen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${IPInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
