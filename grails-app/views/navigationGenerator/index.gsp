<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">HoneyAlarm - Admin Console

 <!--       <img src="${g.resource(dir: 'images', file:'dtag.png')}"></img> -->

        </a>


    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
    <br>
    <g:if test="${role == 'ROLE_ANONYMOUS'}">
        <a href="/login_">_Login_
        </a>    </g:if>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">

                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="/"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                </li>

                <g:if test="${role == 'ROLE_ADMIN'}">
                    <li>
                        <a href="config.html"><i class="fa fa-wrench fa-fw"></i> Config<span class="fa arrow"></span></a>
                        <!-- /.nav-second-level -->
                    </li>
                </g:if>

                <li>
                    <a href="/about"><i class="fa fa-wrench fa-fw"></i> About<span class="fa arrow"></span></a>
                    <!-- /.nav-second-level -->
                </li>


            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>