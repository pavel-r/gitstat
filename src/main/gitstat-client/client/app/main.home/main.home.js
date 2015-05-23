'use strict';

angular.module('gitstatClientApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('main.home', {
        url: '/home',
        templateUrl: 'app/main.home/main.home.html',
        controller: 'MainHomeCtrl'
      });
  });