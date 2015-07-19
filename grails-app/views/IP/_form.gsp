<%@ page import="honeyalarmg2.IP" %>



<div class="fieldcontain ${hasErrors(bean: IPInstance, field: 'text', 'error')} required">
	<label for="text">
		<g:message code="IP.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="text" required="" value="${IPInstance?.text}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: IPInstance, field: 'firstSeen', 'error')} required">
	<label for="firstSeen">
		<g:message code="IP.firstSeen.label" default="First Seen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstSeen" required="" value="${IPInstance?.firstSeen}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: IPInstance, field: 'lastSeen', 'error')} required">
	<label for="lastSeen">
		<g:message code="IP.lastSeen.label" default="Last Seen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastSeen" required="" value="${IPInstance?.lastSeen}"/>

</div>

