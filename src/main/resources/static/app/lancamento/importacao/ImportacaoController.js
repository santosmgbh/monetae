'use strict';

app.controller('ImportacaoController',
    ['FluxoService', 'CentroCustoService', 'LancamentoService', 'ImportacaoService', '$scope','$location','$timeout',   function( FluxoService, CentroCustoService, LancamentoService,  ImportacaoService, $scope, $location, $timeout) {

        var ng = $scope;            
        ng.centrosDeCusto = CentroCustoService.getAll();	
        ng.fluxos = FluxoService.getAll();
        ng.lancamentosImportados = [];
        
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
    						var dadosImportacao=results.data;
    						ng.verificaCamposImportacao(dadosImportacao);
        					ng.colunasImportacao=results.meta.fields;    					
        					
    					}, 50);
    									
    				}

            });
        }
        
        ng.verificaCamposImportacao = function(dadosImportacao){
        	for(var i in dadosImportacao){
        		var registro = dadosImportacao[i];
        		var lancamento = {};
        		lancamento.descricao = registro['DESCRICAO'];
				lancamento.tipoLancamento = registro['VALOR'];
				lancamento.data = registro['DATA'];
				lancamento.valor = registro['VALOR'];
				lancamento.parcelas = registro['PARCELAS'];
        		lancamento.centroCusto = registro['ID_CENTRO_CUSTO'];
        		lancamento.fluxo = registro['ID_FLUXO'];
        		lancamento.user = registro['ID_USER'];
        		
        		
				if(ng.isCamposLancamentoValido(lancamento)){
					registro = ng.formatarCamposLancamento(lancamento);
					ng.lancamentosImportados.push({valido:true, lancamento:registro});					
				}else{
					ng.lancamentosImportados.push({valido:false, lancamento:registro});	
				}
        		        		
        	}
        }
        
        ng.isCamposLancamentoValido = function(lancamento){
        	if(lancamento.descricao && lancamento.descricao != "")
    			if(lancamento.tipoLancamento == 1 || lancamento.tipoLancamento == 2)
    				if(lancamento.data)
    					if(lancamento.valor || lancamento.valor.indexOf(",") == -1)
    						return true;
        	return false;
        }
        
        ng.formatarCamposLancamento = function(lancamento){        	
        	var lancamento = {
    				descricao:registro['DESCRICAO'],
    				tipoLancamento:ng.getTipoLancamento(registro['VALOR']),
    				data:registro['DATA'] ? new Date(registro['DATA']): null,
    				valor:ng.formataValor(registro['VALOR']),
    				parcelas:registro['PARCELAS']?registro['PARCELAS']:1,
    				centroCusto:ng.getIdObjeto(registro['ID_CENTRO_CUSTO']),
    				fluxo:ng.getIdObjeto(registro['ID_FLUXO']),
    				user:registro['ID_USER']        				
    				}; 
    		return lancamento;
        }
        
        
        ng.importarLancamentos = function(){        	
        	var lancamentos = [];
        	for(var i in ng.dadosImportacao){
        		var registro = ng.dadosImportacao[i];        		
        		var lancamento = {
        				descricao:registro['DESCRICAO'],
        				tipoLancamento:ng.getTipoLancamento(registro['VALOR']),
        				data:registro['DATA'] ? new Date(registro['DATA']): null,
        				valor:ng.formataValor(registro['VALOR']),
        				parcelas:registro['PARCELAS']?registro['PARCELAS']:1,
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
        
        ng.getClassValidacao = function(valido){
        	return valido ? 'item-importacao-ok' : 'item-importacao-has-error';
        }
        
    }


    ]);