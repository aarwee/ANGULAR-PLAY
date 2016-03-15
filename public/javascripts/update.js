var demoApp = angular.module('demoApp', []);


        demoApp.controller('UpdateCtrl', function ($scope, $http){

        $scope.updateTo = function(){
var data1 = {"id":parseInt($scope.id),"name":$scope.name,"email":$scope.email,"address":$scope.address,"mobile":$scope.mobile,"emergency":$scope.emergency};


    window.location='/showUser'
       $http({

                     method:'POST',
                     url:'/updateData',
                     data: JSON.stringify(data1),
                     contentType: 'application/json',
                     dataType:'json'
                 })


        }})



