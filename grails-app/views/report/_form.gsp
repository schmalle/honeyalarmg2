<%@ page import="honeyalarmg2.Report" %>



<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'attacker', 'error')} required">
	<label for="attacker">
		<g:message code="report.attacker.label" default="Attacker" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="attacker" required="" value="${reportInstance?.attacker}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'request', 'error')} required">
	<label for="request">
		<g:message code="report.request.label" default="Request" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="request" required="" value="${reportInstance?.request}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="report.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="status" required="" value="${reportInstance?.status}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="report.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="time" required="" value="${reportInstance?.time}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="report.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${reportInstance?.type}"/>

</div>

