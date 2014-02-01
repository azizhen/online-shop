'use strict';

/* Controllers */


function ProductController($scope, $http)  {
  $http.get('http://localhost:8080/productlist/listProducts').
        success(function(data) {
            $scope.products = data;
        });  
}
function MyCtrl2() {
}
MyCtrl2.$inject = [];
