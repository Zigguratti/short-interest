'use strict';

var shortinterestApp = angular.module('shortinterestApp', ['ngRoute', 'shortinterestControllers']);

shortinterestApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/companies', {
        templateUrl: 'partials/company-list.html',
        controller: 'CompanyListCtrl'
      }).
      when('/companies/:ticker', {
        templateUrl: 'partials/company-detail.html',
        controller: 'CompanyDetailCtrl'
      }).
      otherwise({
        redirectTo: '/companies'
      });
  }]);