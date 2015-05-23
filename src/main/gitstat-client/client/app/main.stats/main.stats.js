'use strict';

angular.module('gitstatClientApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('main.stats', {
        url: '/stats',
        templateUrl: 'app/main.stats/main.stats.html',
        controller: 'MainStatsCtrl'
      });
  });