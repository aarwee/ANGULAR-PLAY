var demoApp = angular.module('demoApp', []);


        demoApp.controller('DeleteCtrl', function ($scope, $http){

        $scope.deleteTo = function(){
var data1 = {"id":parseInt($scope.id)};


       return $http({

                    method:'POST',
                     url:'/deleteData',
                    data: JSON.stringify(data1),
                     contentType: 'application/json',
                     dataType:'json'
                 })


        }})




