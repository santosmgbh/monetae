app
		.config([
				'$stateProvider',
				'$urlRouterProvider',
				function($stateProvider, $urlRouterProvider) {

					// An array of state definitions
					var states = [
						
							
							{					
								name : 'main',								
								url : '/main',			
								controller : 'CentroCustoController',		
								templateUrl : 'app/centrocusto/centrocusto-view.html',
							},

							{					
								name : 'centrocusto',								
								url : '/centrocusto',			
								controller : 'CentroCustoController',		
								templateUrl : 'app/centrocusto/centrocusto-view.html',
							},
							
							{					
								name : 'fluxo',								
								url : '/fluxo',			
								controller : 'FluxoController',		
								templateUrl : 'app/fluxo/fluxo-view.html',
							},
							{					
								name : 'lancamento',								
								url : '/lancamento',			
								controller : 'LancamentoController',		
								templateUrl : 'app/lancamento/lancamento-view.html',
							},
							{					
								name : 'estatistica',								
								url : '/estatistica',			
								controller : 'EstatisticaController',		
								templateUrl : 'app/estatisticas/estatistica-view.html',
							},
							
							{					
								name : 'importacao',								
								url : '/importacao',			
								controller : 'ImportacaoController',		
								templateUrl : 'app/lancamento/importacao/importacoes.html',
							},

							{
								name : 'erro',
								url : '/erro',
								templateUrl : 'error-404.html'
							} ]

					
					// Loop over the state definitions and register them
					states.forEach(function(state) {
						$stateProvider.state(state);
					});

					$urlRouterProvider.otherwise('/centrocusto');

				} ]);