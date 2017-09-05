'use strict';
var app = angular.module('monetaeApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/monetae',
    USER_SERVICE_API : 'http://localhost:8080/monetae/user/',
    CENTROCUSTO_SERVICE_API : 'http://localhost:8080/monetae/centroCusto/'
});
