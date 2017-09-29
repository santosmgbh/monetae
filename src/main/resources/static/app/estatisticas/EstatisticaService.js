'use strict';

app.factory('EstatisticaService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

            var factory = {
            		getLineChartLancamentos: getLineChartLancamentos,                
            };

            return factory;

            function getLineChartLancamentos(dtIni, dtFim, result, error) {                
                $http.get(urls.ESTATISCA_SERVICE_API+"getLineChartLancamentos",
        		{
                    params: { dtIni: dtIni, dtFim: dtFim }
                })
                    .then(
                        function (response) {
                            if(result)
                            	result(response.data)                            
                        },
                        function (errResponse) {
                        	if(error)
                        		error(errResponse);
                        }
                    );                
            }


        }
    ]);