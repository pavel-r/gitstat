'use strict';

angular.module('gitstatClientApp')
  .controller('MainStatsCtrl', function ($scope, $http) {
  	$scope.stats = [];
  	$scope.fields = [];
  	$http.get('/api/stats').success(function(data){
  		$scope.stats = data;
  		if(data.length > 0){
  			for(var field in data[0]){
  				if(data[0].hasOwnProperty(field)){
  					$scope.fields.push(field);
  				}
  			}
  		}
  		$scope.data = data.map(function(item){
  			return item.added;
  		});
  		$scope.labels =data.map(function(item){
  			return item.author;
  		});
  	});
  });
