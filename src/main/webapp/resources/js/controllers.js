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
