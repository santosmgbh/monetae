'use strict';

app.factory('EstatisticaService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

            var factory = {
                loadAll: loadAll,                
            };

            return factory;

            function getLineChartLancamentos(ok) {                
                $http.get(urls.ESTATISCA_SERVICE_API+"getLineChartLancamentos",{
                    params: { dtIni: '2017-06', dtFim: '2017-11' }
                })
                    .then(
                        function (response) {
                            if(ok)
                            	ok(response.data)                            
                        },
                        function (errResponse) {                            
                        }
                    );                
            }


        }
    ]);