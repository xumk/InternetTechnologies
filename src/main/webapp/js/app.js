var app = angular.module('app', ['ngCookies', 'ngRoute', 'ngSanitize']);

app.config(function ($routeProvider, $cookies) {
    $cookies.test = "test";
    $routeProvider
            .when('/', {
                templateUrl: 'pages/page4.html',
                controller: 'lb4'
            })
            // первая лабораторная работ
            .when('/lb1', {
                templateUrl: 'pages/page1.html',
                controller: 'lb1'
            })
            // вторая лабораторная работа
            .when('/lb2', {
                templateUrl: 'pages/page2.html',
                controller: 'lb2'
            })
            // третья лабораторная работа
            .when('/lb3', {
                templateUrl: 'pages/page3.html',
                controller: 'lb3'
            });
});

app.controller('AppCtrl', function ($rootScope) {
    $rootScope.isNeedShowAnnonce = false;
    $rootScope.htmlTextAnnonce = "";
    $rootScope.isShowMenu = true;
});