'use strict';

/* Controllers */


function ProductController($scope, $http)  {
  $http.get('http://localhost:8080/productlist/listProducts').success(function(data) {
            $scope.products = data;
        });  
}

function ProductDetailController($scope, $routeParams, $http){
    $http.get('http://localhost:8080/productlist/product?productID=' + $routeParams.productID).success(function(data) {
      $scope.product = data;
    });
}

function UserProfileController($scope, $routeParams, $http, $rootScope){
    
    $rootScope.username = 'aziz';
    
    $http.get('http://localhost:8080/productlist/addProductToBasket?username='+ $rootScope.username +'&productID=' + $routeParams.productID).success(function(data) {
      $scope.products = data;
    });
    
    $http.get('http://localhost:8080/productlist/userDetails?username=' + $rootScope.username).success(function(data) {
      $scope.user = data;
    });
    
}

