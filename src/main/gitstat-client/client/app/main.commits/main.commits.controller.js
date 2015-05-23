'use strict';

angular.module('gitstatClientApp')
  .controller('MainCommitsCtrl', function ($scope, Commit) {
    $scope.commits = Commit.query();
  });
