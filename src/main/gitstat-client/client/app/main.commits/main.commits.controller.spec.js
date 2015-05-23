'use strict';

describe('Controller: MainCommitsCtrl', function () {

  // load the controller's module
  beforeEach(module('gitstatClientApp'));

  var MainCommitsCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainCommitsCtrl = $controller('MainCommitsCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
