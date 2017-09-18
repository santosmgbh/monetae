'use strict';

app.factory('EstatisticaService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

            var factory = {
                loadAll: loadAll,                
            };

            return factory;

            function loadAll() {
                var deferred = $q.defer();
                $http.get(urls.ESTATISCA_SERVICE_API)
                    .then(
                        function (response) {
                            $sessionStorage.dadosGrafico = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
          

        }
    ]);