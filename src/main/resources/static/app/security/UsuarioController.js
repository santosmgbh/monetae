'use strict';

app.controller('UsuarioController',
    ['UsuarioService', '$scope','$location','$timeout',   function( UsuarioService,  $scope, $location, $timeout) {

        var ng = $scope;
        ng.obj = {};
        ng.lista=[];
        ng.quantity = 25;


        ng.successMessage = '';
        ng.errorMessage = '';
        
        ng.init = function(){
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
            ng.setEdicao(false);
        }
        

        ng.create = function create(obj) {
            CentroCustoService.create(obj)
                .then(
                    function (response) {
                        ng.successMessage = 'created successfully';
                        ng.errorMessage='';
                        ng.obj={};
                        ng.getAll();
//                        $scope.myForm.$setPristine();   //reset form                     
                    },
                    function (errResponse) {
                        ng.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        ng.successMessage='';
                    }
                );
        }

        ng.update = function update(obj, id){
            CentroCustoService.update(obj, id)
                .then(
                    function (response){
                        ng.successMessage='updated successfully';
                        ng.errorMessage='';
                        ng.getAll();
                    },
                    function(errResponse){
                        ng.errorMessage='Error while updating User '+errResponse.data;
                        ng.successMessage='';
                    }
                );
        }


        ng.remove = function remove(id){
            CentroCustoService.remove(id)
                .then(
                    function(){
                        console.log(id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing '+id +', Error :'+errResponse.data);
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
            ng.successMessage='';
            ng.errorMessage='';
            ng.obj={};
            $scope.myForm.$setPristine(); //reset Form
        }        
        
        ng.init();
    }


    ]);