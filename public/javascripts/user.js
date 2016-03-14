var demoApp = angular.module('demoApp', []);
        demoApp.controller('UserCtrl', function ($scope, $http){
          $http.get('/show').success(function(data) {
            $scope.dataList=data;
          });
        });

