
<div class="col-lg-3 col-md-6">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge"><%=honeypots%></div>
                    <div>Active honeypots!</div>
                </div>
            </div>
        </div>
        <a href="/Honeypot">
            <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>


<div class="col-lg-3 col-md-6">
    <div class="panel panel-red">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-support fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${numberOfReports}</div>
                    <div>New reports</div>
                </div>
            </div>
        </div>
        <a href="/HandleReports">
            <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>


<div class="col-lg-3 col-md-6">
    <div class="panel panel-green">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-database fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge"><%=ips%></div>
                    <div>Overall IPs</div>
                </div>
            </div>
        </div>
        <a href="/IP">
            <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-yellow">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-shopping-cart fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${numberOfAlarms}</div>
                    <div>Confirmed alarms</div>
                </div>
            </div>
        </div>
        <a href="/ShowAlarms">
            <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>