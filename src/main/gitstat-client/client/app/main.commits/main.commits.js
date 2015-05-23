'use strict';

angular.module('gitstatClientApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('main.commits', {
        url: '/commits',
        templateUrl: 'app/main.commits/main.commits.html',
        controller: 'MainCommitsCtrl'
      });
  });