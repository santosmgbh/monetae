'use strict';

app.factory('ImportacaoService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

            var factory = {
        		importarLancamentos: importarLancamentos
            };

            return factory;

            function importarLancamentos(lancamentos, ok, error) {
                var deferred = $q.defer();
                $http.post(urls.IMPORTACAO_SERVICE_API+"importarLancamentos", lancamentos)
                    .then(
                        function (response) {
                        	if(ok)
                        		ok(response.data);
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                        	if(error)
                        		error(errResponse);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);