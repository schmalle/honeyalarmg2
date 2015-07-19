<%@ page import="honeyalarmg2.UIReport" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'UIReport.label', default: 'UIReport')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-UIReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-UIReport" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list UIReport">

        <g:if test="${UIReportInstance?.time}">
            <li class="fieldcontain">
                <span id="time-label" class="property-label"><g:message code="UIReport.time.label"
                                                                        default="Time"/></span>

                <span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${UIReportInstance}"
                                                                                        field="time"/></span>

            </li>
        </g:if>

        <g:if test="${UIReportInstance?.type}">
            <li class="fieldcontain">
                <span id="type-label" class="property-label"><g:message code="UIReport.type.label"
                                                                        default="Type"/></span>

                <span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${UIReportInstance}"
                                                                                        field="type"/></span>

            </li>
        </g:if>

        <g:if test="${UIReportInstance?.text}">
            <li class="fieldcontain">
                <span id="text-label" class="property-label"><g:message code="UIReport.text.label"
                                                                        default="Text"/></span>

                <span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${UIReportInstance}"
                                                                                        field="text"/></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: UIReportInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${UIReportInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
