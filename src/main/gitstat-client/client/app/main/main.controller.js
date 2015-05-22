'use strict';

angular.module('gitstatClientApp')
  .controller('MainCtrl', function ($scope, $http) {
    $scope.commits = [];

    $http.get('/api/commits').success(function (commits) {
      $scope.commits = commits;
    });
  });
