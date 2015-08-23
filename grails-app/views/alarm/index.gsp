
<%@ page contentType="text/html;charset=UTF-8" %>


<body>

<!-- Show all available reports -->

<g:each in="${reports}" var="report">

    <a href="#" class="list-group-item">
        <i class="fa fa-comment fa-fw"></i> ${report.text}
        <span class="pull-right text-muted small"><em>${report.time}</em>
        </span>
    </a>

</g:each>


</body>
