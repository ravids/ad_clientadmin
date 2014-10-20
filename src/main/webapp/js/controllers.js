'use strict';

/* Controllers */

var clientadminControllers = angular.module('clientadminControllers', []);

clientadminControllers.controller('HomeCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
	function($rootScope, $scope, $routeParams, $http) {
		$scope.createUser = function() {
			console.log('BEGIN createUser');
			
			$http.post('api/uam', {
                    "userName": $scope.userName
					,"firstName": $scope.firstName
					,"lastName": $scope.lastName
			})
			.success(function(data, status, headers, config) {
				console.log('data = ' , data);
				$scope.userName = '';
				$scope.firstName = '';
				$scope.lastName = '';
				$scope.newUserId = data;
			})
			.error(function(data, status, headers, config) {
				console.log('error: data = ' , data);
			});
		};
		
		$scope.searchUser = function() {
			$http.get('api/uam/' + $scope.searchUserId)
			.success(function(data, status, headers, config) {
				console.log('data = ' , data);
				$scope.person = data;
			})
			.error(function(data, status, headers, config) {
				console.log('error: data = ' , data);
			});
		};
	}
]);



clientadminControllers.controller('CompanyCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
    function($rootScope, $scope, $routeParams, $http) {
        $scope.createCompany = function() {
            console.log('BEGIN createCompany');

            $http.post('api/company', {
                "companyName": $scope.companyName
            })
                .success(function(data, status, headers, config) {
                    console.log('data = ' , data);
                    $scope.companyName = '';
                    $scope.newCompanyId = data;
                })
                .error(function(data, status, headers, config) {
                    console.log('error: data = ' , data);
                });
        };

        $scope.searchCompany = function() {
            $http.get('api/company/' + $scope.searchCompanyId)
                .success(function(data, status, headers, config) {
                    console.log('data = ' , data);
                    $scope.company = data;
                })
                .error(function(data, status, headers, config) {
                    console.log('error: data = ' , data);
                });
        };
    }
]);