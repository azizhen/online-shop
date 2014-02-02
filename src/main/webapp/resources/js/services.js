'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var myAppServices = angular.module('myApp.services', []);
myAppServices.factory('LoginStatusService', [function() {
	var loginData = {
		isLoggedIn: true,
		username: 'aziz'
	};
	return loginData;
}]);