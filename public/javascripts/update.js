var demoApp = angular.module('demoApp', []);


        demoApp.controller('UpdateCtrl', function ($scope, $http){

        $scope.updateTo = function(){
var data1 = {"old-name":$scope.old-name,"name":$scope.name,"email":$scope.email,"password":$scope.password,"mobile":$scope.mobile,"admin":$scope.admin};


       return $http({

                     method:'POST',
                     url:'/updateData',
                     data: JSON.stringify(data1),
                     contentType: 'application/json',
                     dataType:'json'
                 })


        }})


