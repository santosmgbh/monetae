'use strict';

app.controller('CentroCustoController',
    ['CentroCustoService', '$scope','$location',   function( CentroCustoService, $scope, $location) {

        var ng = $scope;
        ng.centroCusto = {};
        ng.centrosCustos=[];


        ng.successMessage = '';
        ng.errorMessage = '';
        ng.done = false;

        ng.onlyIntegers = /^\d+$/;
        ng.onlyNumbers = /^\d+([,.]\d+)?$/;
        
        ng.init = function(){
        	console.log("init!")
        	ng.getAll();
        }

        ng.submit = function submit() {
            console.log('Submitting');
            if (ng.centroCusto.id === undefined || ng.centroCusto.id === null) {
                console.log('Saving New User', ng.centroCusto);
                ng.create(ng.centroCusto);
            } else {
            	ng.update(ng.centroCusto, ng.centroCusto.id);
                console.log('User updated with id ', ng.centroCusto.id);
            }
            ng.getAll();
            $location.path("centrocusto");
        }

        ng.create = function create(centroCusto) {
            console.log('About to create centroCusto');
            CentroCustoService.create(centroCusto)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        ng.successMessage = 'User created successfully';
                        ng.errorMessage='';
                        ng.done = true;
                        ng.centroCusto={};
//                        $scope.myForm.$setPristine();   //reset form                     
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        ng.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        ng.successMessage='';
                    }
                );
        }

        ng.update = function update(centroCusto, id){
            console.log('About to update centroCusto');
            CentroCustoService.update(centroCusto, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        ng.successMessage='User updated successfully';
                        ng.errorMessage='';
                        ng.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        ng.errorMessage='Error while updating User '+errResponse.data;
                        ng.successMessage='';
                    }
                );
        }


        ng.remove = function remove(id){
            console.log('About to remove User with id '+id);
            CentroCustoService.remove(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing centroCusto '+id +', Error :'+errResponse.data);
                    }
                );
        }


        ng.getAll = function getAll(){
        	ng.centrosCustos = CentroCustoService.getAll();
        	console.log(ng.centrosCustos);
        }

        ng.edit = function edit(centroCusto) {        	
        	ng.centroCusto = centroCusto;
        	console.log(ng.centroCusto);
        	$location.path("centrocusto/create");
        }
        
        ng.reset = function reset(){
            ng.successMessage='';
            ng.errorMessage='';
            ng.centroCusto={};
            $scope.myForm.$setPristine(); //reset Form
        }        
        
        ng.init();
    }


    ]);