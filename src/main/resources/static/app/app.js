'use strict';
var app = angular.module('monetaeApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/monetae',
    USER_SERVICE_API : 'http://localhost:8080/monetae/user/',
    CENTROCUSTO_SERVICE_API : 'centroCusto/',
	FLUXO_SERVICE_API : 'fluxo/',
	LANCAMENTO_SERVICE_API : 'lancamento/',
	ESTATISCA_SERVICE_API : 'estatistica/',
	IMPORTACAO_SERVICE_API : 'importacao/'
});

app.controller('InitController',
	    ['UsuarioService', '$scope','$location','$timeout','$window',   function( UsuarioService,  $scope, $location, $timeout, $window) {
	    	 var ng = $scope;
	    	 ng.ALERT_SUCCESS = "alert-success";
	    	 ng.ALERT_INFO = "alert-info";
	    	 ng.ALERT_WARNING = "alert-warning";	    	 
	    	 ng.ALERT_DANGER = "alert-danger";
	    	 	    	 
	    	 
	    	 ng.logout = function(){	    		 
	    		 UsuarioService.logout();
	    		 $window.location.reload();
	    	 }
	    	 
	    	 ng.loading = function(visivel){
	    		 $timeout(function(){
	    			 console.log("loading")
		    		 console.log(visivel)
		    		 if(visivel)
		    			 $("#modal-progress").modal('show');
		    		 else
		    			 $("#modal-progress").modal('hide');	 
	    		 }, 100);
	    	 }
	    	 
	    	 ng.alert = function(tipo, message){
	    		 $("#main_topo_alert").show();
	    		 $("#main_topo_alert").removeClass("alert "+ng.ALERT_SUCCESS+" "+ng.ALERT_INFO+" "+ng.ALERT_WARNING+" "+ng.ALERT_DANGER);
	    		 $("#main_topo_alert").addClass("alert "+tipo);
		    	 $("#main_topo_alert").html("<strong>Sucesso!</strong> " + message);
		    	 $timeout(function(){
		    		 $("#main_topo_alert").hide();
		    	 }, 2000);
	    	 }
	    	 
	    }]);
