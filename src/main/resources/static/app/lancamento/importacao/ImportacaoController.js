'use strict';

app.controller('ImportacaoController',
    ['FluxoService', 'CentroCustoService', 'LancamentoService', '$scope','$location','$timeout',   function( FluxoService, CentroCustoService, LancamentoService,  $scope, $location, $timeout) {

        var ng = $scope;    
        var fileInput = document.getElementById('importLancamento');
        // parse when file loaded, then print the result to console
        fileInput.addEventListener('change', function (e) {     	
            var file = e.target.files[0];
            ng.importLancamentos(file);
        });
        
        ng.importLancamentos = function(file){                   
    			Papa.parse(file,{
    				delimiter:";",
    				encoding: "default",
    				header:true,
    				dynamicTyping:true,
    				complete:function(results){
    					$timeout(function(){
    						ng.dadosImportacao=results.data;			    					
        					ng.colunasImportacao=results.meta.fields;    					
        					console.log(ng.dadosImportacao);
        					console.log(ng.colunasImportacao);	
    					}, 50);
    									
    				}

            });
        }
        
    }


    ]);