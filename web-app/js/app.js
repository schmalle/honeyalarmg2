var app = angular.module("honeyalarm", []);

//
// Directly retrieve the JSON data from the REST Interface via the class Name UIReport
//

app.controller("ReportController", function($scope, $http) {
    $http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
    $http.get('/UIReport.json').
        success(function(data, status, headers, config) {
            $scope.reportsAngular = data;
        });
});


//
// Directly retrieve the JSON data from the REST Interface via the class Name Report
//

app.controller("AlarmController", function($scope, $http) {
    $http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
    $http.get('/Report.json').
        success(function(data, status, headers, config) {
            $scope.alarms = data;
        });
});
