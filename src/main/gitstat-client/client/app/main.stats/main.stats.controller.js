'use strict';

angular.module('gitstatClientApp')
  .controller('MainStatsCtrl', function ($scope, $http) {
  	$scope.stats = [];
  	$http.get('/api/stats', function(data){
  		$scope.stats = data;
  	});
  });
