'use strict';

app.controller('CentroCustoController',
    ['CentroCustoService', '$scope','$location','$timeout',   function( CentroCustoService,  $scope, $location, $timeout) {

        var ng = $scope;
        ng.obj = {};
        ng.lista=[];
        ng.quantity = 25;

        
        ng.init = function(){
        	
        	ng.loading(true);
        	CentroCustoService.loadAll().then(function(){
        		ng.getAll();
        		ng.loading(false);
        	}, function(error){
        		console.log(error);
        		ng.alert(ng.ALERT_DANGER, "Ocorreu um problema!");
        	});
        	
        }
        
        ng.setEdicao = function(isEdicao){
        	ng.isEdicao = isEdicao;
        }
                

        ng.submit = function submit() {          	
            if (ng.obj.id === undefined || ng.obj.id === null) {
                ng.create(ng.obj);
            } else {
            	ng.update(ng.obj, ng.obj.id);
            }                        
        }
        

        ng.create = function create(obj) {
        	ng.loading(true);
            CentroCustoService.create(obj)
                .then(
                    function (response) {
                    	ng.loading(false);
                        ng.obj={};
                        ng.getAll();                        
                        ng.alert(ng.ALERT_SUCCESS, "Salvo com sucesso!");
                        ng.setEdicao(false);
                    },
                    function (errResponse) {
                    	ng.loading(false);
                    	ng.alert(ng.ALERT_DANGER, "Problema ao salvar!");                        
                    }
                );
        }

        ng.update = function update(obj, id){
        	ng.loading(true);
            CentroCustoService.update(obj, id)
                .then(
                    function (response){
                    	ng.loading(false);
                        ng.getAll();         
                        ng.setEdicao(false);
                    },
                    function(errResponse){
                    	ng.loading(false);
                    	ng.alert(ng.ALERT_DANGER, "Problema ao atualizar!");
                    }
                );
        }


        ng.remove = function remove(id){
        	ng.loading(true);
            CentroCustoService.remove(id)
                .then(
                    function(){
                    	ng.loading(false);
                    	ng.alert(ng.ALERT_SUCCESS, "Removido com sucesso!");    
                    },
                    function(errResponse){
                    	ng.loading(false);
                    	ng.alert(ng.ALERT_DANGER, "Problema ao remover!");
                    }
                );
        }


        ng.getAll = function getAll(){
        	$timeout(function(){       
        		ng.lista = CentroCustoService.getAll();        		
        	}, 50);
        	        	
        }

        ng.edit = function edit(obj) {        	
        	ng.setEdicao(true);
        	ng.obj = obj;        	        
        }
        
        ng.reset = function reset(){            
            ng.obj={};
            $scope.myForm.$setPristine(); //reset Form
        }        
        
        ng.init();
    }


    ]);