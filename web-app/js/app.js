var app = angular.module("honeyalarm", []);

app.controller("ReportController", function($scope, $http) {
    $http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
    $http.get('/UIReport.json').
        success(function(data, status, headers, config) {
            $scope.reports = data;
        });
});
