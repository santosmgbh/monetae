'use strict';

app.controller('FluxoController',
    ['FluxoService', '$scope','$location','$timeout',   function( FluxoService,  $scope, $location, $timeout) {

        var ng = $scope;
        ng.obj = {};
        ng.lista=[];
        ng.quantity = 25;

        
        ng.init = function(){
        	FluxoService.loadAll();
        	ng.getAll();
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
        	FluxoService.create(obj)
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
        	FluxoService.update(obj, id)
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
        	FluxoService.remove(id)
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
        		ng.lista = FluxoService.getAll();        		
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