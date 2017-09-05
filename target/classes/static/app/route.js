app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
	
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list-centro-custo',
                controller:'CentroCustoController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, CentroCustoService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        CentroCustoService.loadAll().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('centrocusto', {
                url: '/centrocusto',
                controller: 'CentroCustoController',
            	templateUrl: 'app/centrocusto/centrocusto-view.html',                
                                              
            })
            .state('centrocusto/cadastro', {
                url: '/centrocusto/create',        
                parent: centrocusto,
                templateUrl: 'app/centrocusto/centrocusto-cadastro.html'                                
            })
            .state('fluxo', {
                url: '/fluxo',                
                templateUrl: 'table-view.html'                                
            })
            .state('erro', {
                url: '/erro',                
                templateUrl: 'error-404.html'                                
            });
        $urlRouterProvider.otherwise('/erro');
        
        
    }]);