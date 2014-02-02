'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/productList', {templateUrl: 'resources/partials/productList.html', controller: ProductController});
    $routeProvider.when('/products/:productID', {templateUrl: 'resources/partials/productDetail.html',controller: ProductDetailController});
    $routeProvider.when('/basket/:productID', {templateUrl: 'resources/partials/userProfile.html',controller: UserProfileController});
    $routeProvider.otherwise({redirectTo: '/productList'});
  }]);
