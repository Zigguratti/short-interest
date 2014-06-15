var shortinterestControllers = angular.module('shortinterestControllers', []);

shortinterestApp.controller('CompanyListCtrl',
    function ($scope, $http) {
        $http.get('feed/companies?size=200').success(function(data) {
            $scope.companies = data._embedded.companies;
        })
    }
);

shortinterestApp.controller('CompanyDetailCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
        $http.get('feed/companies/' + $routeParams.ticker).success(function(data) {
            $scope.company = data
        })
}]);