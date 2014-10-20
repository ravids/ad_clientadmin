'use strict';

/* App Module */

var clientadminApp = angular.module('clientadminApp', [
	'ngRoute',
	'clientadminControllers'
]);

clientadminApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/home', {
				templateUrl: 'partials/user.html',
				controller: 'HomeCtrl'
			}).
            when('/company', {
                templateUrl: 'partials/company.html',
                controller: 'CompanyCtrl'
            }).
			otherwise({
				redirectTo: '/home'
			});
	}
]);
