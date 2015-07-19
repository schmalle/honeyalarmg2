<%@ page import="honeyalarmg2.UIReport" %>



<div class="fieldcontain ${hasErrors(bean: UIReportInstance, field: 'time', 'error')} required">
    <label for="time">
        <g:message code="UIReport.time.label" default="Time"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="time" required="" value="${UIReportInstance?.time}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: UIReportInstance, field: 'type', 'error')} required">
    <label for="type">
        <g:message code="UIReport.type.label" default="Type"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="type" required="" value="${UIReportInstance?.type}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: UIReportInstance, field: 'text', 'error')} required">
    <label for="text">
        <g:message code="UIReport.text.label" default="Text"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="text" required="" value="${UIReportInstance?.text}"/>

</div>

