var demoApp = angular.module('demoApp', []);
        demoApp.controller('UserCtrl', function ($scope, $http){
          $http.get('/show').success(function(data) {
            $scope.dataList=data;
           });

        $scope.deleteTo = function(){
        var data1 = {"id":parseInt($scope.id)};
        window.location='/showUser'
        $http({

                            method:'POST',
                             url:'/deleteData',
                            data: JSON.stringify(data1),
                             contentType: 'application/json',
                             dataType:'json'
                         });

        };
        });

