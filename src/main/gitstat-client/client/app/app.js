'use strict';

angular.module('gitstatClientApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ui.router',
    'ui.bootstrap',
    'chart.js'
  ])
  .config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider
      .otherwise('/main/home');

    $locationProvider.html5Mode(true);
  });
