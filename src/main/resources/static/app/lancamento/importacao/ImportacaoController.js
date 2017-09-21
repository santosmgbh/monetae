'use strict';

app.controller('ImportacaoController',
    ['FluxoService', 'CentroCustoService', 'LancamentoService', 'ImportacaoService', '$scope','$location','$timeout',   function( FluxoService, CentroCustoService, LancamentoService,  ImportacaoService, $scope, $location, $timeout) {

        var ng = $scope;            
        ng.centrosDeCusto = CentroCustoService.getAll();	
        ng.fluxos = FluxoService.getAll();
        console.log( ng.centrosDeCusto);
        var fileInput = document.getElementById('importLancamento');
        // parse when file loaded, then print the result to console
        fileInput.addEventListener('change', function (e) {     	
            var file = e.target.files[0];
            ng.parseFile(file);
        });
        
        ng.parseFile = function(file){                   
    			Papa.parse(file,{
    				delimiter:";",
    				encoding: "default",
    				header:true,
    				dynamicTyping:true,
    				complete:function(results){
    					$timeout(function(){
    						ng.dadosImportacao=results.data;
    						ng.verificaCamposImportacao();
        					ng.colunasImportacao=results.meta.fields;    					
        					
    					}, 50);
    									
    				}

            });
        }
        
        ng.verificaCamposImportacao = function(){
        	for(var i in ng.dadosImportacao){
        		if(!ng.dadosImportacao[i]["DESCRICAO"])
        			ng.dadosImportacao.splice(i, 1);
        	}
        }
        
        ng.importarLancamentos = function(){        	
        	var lancamentos = [];
        	for(var i in ng.dadosImportacao){
        		var registro = ng.dadosImportacao[i];
        		debugger
        		var lancamento = {
        				descricao:registro['DESCRICAO'],
        				tipoLancamento:ng.getTipoLancamento(registro['VALOR']),
        				data:registro['DATA'] != null ? new Date(registro['DATA']): null,
        				valor:ng.formataValor(registro['VALOR']),
        				parcelas:registro['PARCELAS'],
        				centroCusto:ng.getIdObjeto(registro['ID_CENTRO_CUSTO']),
        				fluxo:ng.getIdObjeto(registro['ID_FLUXO']),
        				user:registro['ID_USER']        				
        				};  
        		lancamentos.push(lancamento);
        	}
        	ng.loading(true);
        	ImportacaoService.importarLancamentos(lancamentos, function(){
        		LancamentoService.loadAll();
        		ng.loading(false);
        		ng.alert(ng.ALERT_SUCCESS, "Lançamentos importados com sucesso!");        		
            	$location.path("/lancamento");
        	}, function(){
        		ng.alert(ng.ALERT_DANGER, "Problema ao importar dados!");
        	});
        	
        }
        
        ng.getTipoLancamento = function(valor){
        	return parseInt(valor) > 0? 1 : 2;
        }
        
        ng.formataValor = function(valor){     
        	if(valor && typeof valor === 'string')
        		valor = valor.replace(",", ".")
    		if(valor && parseInt(valor) < 0){    			
    			valor = (valor+"").replace("-", "");
    		}
        	return valor;
        }
        
        ng.getIdObjeto = function(valor){
        	if(valor)
        		valor = valor.id;
        	else
        		valor = null;
        	return valor;
        }
        
        
    }


    ]);