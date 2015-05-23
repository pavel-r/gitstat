'use strict';

angular.module('gitstatClientApp')
  .controller('MainCommitsDetailsCtrl', function ($scope, $stateParams, Commit) {
    $scope.commit = Commit.get({sha: $stateParams.sha});
  });
