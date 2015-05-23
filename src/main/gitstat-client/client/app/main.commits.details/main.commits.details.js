'use strict';

angular.module('gitstatClientApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('main.commits.details', {
        url: '/:sha',
        templateUrl: 'app/main.commits.details/main.commits.details.html',
        controller: 'MainCommitsDetailsCtrl'
      });
  });