var demoApp = angular.module('demoApp', []);


        demoApp.controller('DeleteCtrl', function ($scope, $http){

        $scope.deleteTo = function(){
var data1 = {"name":$scope.name};


       return $http({

                    method:'POST',
                     url:'/deleteData',
                    data: JSON.stringify(data1),
                     contentType: 'application/json',
                     dataType:'json'
                 })


        }})


