/**
 * Created by Roi Zlotolov on 10/5/2014.
 */
var app = angular.module("myApp", ['ngRoute', 'ngTable', 'ui.bootstrap']);





app.controller('QuestionsController', ['$scope', '$http', '$filter', 'ngTableParams', function ($scope, $http, $filter, ngTableParams) {

    $scope.templates =
        [
            { name: 'template1.html', url: 'templates/wellcome.html'},
            { name: 'template2.html', url: 'templates/question0.html'},
            { name: 'template2.html', url: 'templates/question1.html'},
            { name: 'template2.html', url: 'templates/question2.html'},
            { name: 'template2.html', url: 'templates/question3.html'},
            { name: 'template2.html', url: 'templates/question4.html'},
            { name: 'template2.html', url: 'templates/question5.html'},
            { name: 'template2.html', url: 'templates/question6.html'},
            { name: 'template2.html', url: 'templates/question7.html'},
            { name: 'template2.html', url: 'templates/question8.html'},
            { name: 'template2.html', url: 'templates/question9.html'},
            { name: 'template2.html', url: 'templates/question10.html'},
            { name: 'template2.html', url: 'templates/question11.html'},
            { name: 'template2.html', url: 'templates/question12.html'},
            { name: 'template2.html', url: 'templates/question13.html'},
            { name: 'template2.html', url: 'templates/question14.html'},
            { name: 'template2.html', url: 'templates/question15.html'},
            { name: 'template2.html', url: 'templates/question16.html'},
            { name: 'template2.html', url: 'templates/question17.html'},
            { name: 'template2.html', url: 'templates/question18.html'},
            { name: 'template2.html', url: 'templates/question19.html'},
            { name: 'template2.html', url: 'templates/question20.html'},
            { name: 'template2.html', url: 'templates/Final.html'}
        ];

    $scope.counter = 0;
    $scope.jump = function (counterNumber) {
        $scope.counter = counterNumber;
        $location.hash(counterNumber);
        console.log($location.hash());
        $anchorScroll();
    }
    $scope.searchRecepies = function () {

        var ingredients =
        [
            {
                "id": 3013,
                "name": "beet",
                "quantity":	"1 - pound"
            },
            {
                "id": 3026,
                "name": "carrot",
                "quantity":	"1 -  pound"
            }
         ]


        $http({
            method: 'POST',
            url: "http://127.0.0.1:4567/getRecepiesByIngredients",
            data: ingredients,
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));
            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchFruits = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getFruits",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchSpices = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getSpices",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchMeat = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getMeat",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchSauces = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getSauce",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchVegetables = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getVegetables",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

    $scope.searchMilk = function () {

        $http({
            method: 'GET',
            url: "http://127.0.0.1:4567/getMilk",
            data: '',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
        }).success(function (response) {
                console.log(JSON.stringify(response));

            }
        ).error(function (response) {
                console.log("-----Error Recepies-------");
                //console.log(response);
            });
    };

}]);

