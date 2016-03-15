var demoApp = angular.module('demoApp', []);


        demoApp.controller('AddCtrl', function ($scope, $http){

        $scope.addTo = function(){
var data1 = {"name":$scope.name,"email":$scope.email,"address":$scope.address,"mobile":$scope.mobile,"emergency":$scope.emergency};

        window.location='/showUser'
        $http({
                     method:'POST',
                     url:'/addData',
                     data: JSON.stringify(data1),
                     contentType: 'application/json',
                     dataType:'json'
                 })


        }})


