'use strict';

describe('Controller: MainCommitsDetailsCtrl', function () {

  // load the controller's module
  beforeEach(module('gitstatClientApp'));

  var MainCommitsDetailsCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainCommitsDetailsCtrl = $controller('MainCommitsDetailsCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
