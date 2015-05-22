'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('gitstatClientApp'));

  var MainCtrl,
    scope,
    $httpBackend;

  // Initialize the controller and a mock scope
  beforeEach(inject(function (_$httpBackend_, $controller, $rootScope) {
    $httpBackend = _$httpBackend_;
    $httpBackend.expectGET('/api/commits')
      .respond([{
        author: 'Pavel'
      }, {
        author: 'Vasya'
      }]);

    scope = $rootScope.$new();
    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of commits to the scope', function () {
    $httpBackend.flush();
    expect(scope.commits.length).toBe(2);
  });
});
