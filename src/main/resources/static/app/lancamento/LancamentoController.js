'use strict';

app.controller('LancamentoController',
    ['FluxoService', 'CentroCustoService', 'LancamentoService', '$scope','$location','$timeout',   function( FluxoService, CentroCustoService, LancamentoService,  $scope, $location, $timeout) {

        var ng = $scope;    
        ng.treta = "123123123";
        ng.obj = {fluxo:{id:null}, centroCusto:{id:null}, tipoLancamento: {id:"1", nome:"RECEBIMENTO"}};
        ng.lista=[];
        ng.quantity = 25;
        ng.centrosCustos = [];
        ng.fluxos = [];
        ng.tiposLancamento = [{id:"1", nome:"RECEBIMENTO"}, {id:"2", nome:"PAGAMENTO"}];
        
        ng.init = function(){
        	ng.getAll();
        	ng.centrosCustos = CentroCustoService.getAll();
        	ng.fluxos = FluxoService.getAll();        	
        	
        }
        
        ng.importarLancamentos = function(){
        	$location.path("/importacao");
        };
       
        
        ng.setEdicao = function(isEdicao){
        	ng.isEdicao = isEdicao;
        }
                

        ng.submit = function submit() {
        	ng.obj.valor = $("#valor").val().replace("R$ ", "");
        	ng.obj.data = new Date($("#data").val());
            if (ng.obj.id === undefined || ng.obj.id === null) {
                ng.create(ng.obj);
            } else {
            	ng.update(ng.obj, ng.obj.id);
            }                              
        }
        

        ng.create = function create(obj) {
        	ng.loading(true);
            LancamentoService.create(obj)
                .then(
                    function (response) {   
                    	ng.reset();
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
            LancamentoService.update(obj, id)
                .then(
                    function (response){          
                    	ng.reset();
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
            LancamentoService.remove(id)
                .then(
                    function(){
                    	ng.reset();
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
        		ng.lista = LancamentoService.getAll();        		
        	}, 50);
        	        	
        }

        ng.edit = function edit(obj) {             	
        	console.log(obj);
        	ng.setEdicao(true);
        	ng.obj = obj;        	        
        }
        
        ng.reset = function reset(){            
        	ng.obj = {fluxo:{id:null}, centroCusto:{id:null}, tipoLancamento: {id:"1", nome:"RECEBIMENTO"}};
        }        
        
        ng.init();
    }


    ]);