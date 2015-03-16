'use strict';

angular.module('app', [
  'ngRoute',
  'app.controllers'])
    .config(function ($routeProvider) {
      $routeProvider
          .when('/product', {
            controller: 'ProductController',
            templateUrl: 'resources/product/layout.html',
            resolve: {}
          });
    });