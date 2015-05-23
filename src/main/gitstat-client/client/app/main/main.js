'use strict';

angular.module('gitstatClientApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('main', {
      	abstract: true,
        url: '/main',
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      });
  });
