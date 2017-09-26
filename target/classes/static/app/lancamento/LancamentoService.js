'use strict';

app.factory('LancamentoService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

            var factory = {
                loadAll: loadAll,
                getTiposLancamento: getTiposLancamento,
                getAll: getAll,
                get: get,
                create: create,
                update: update,
                remove: remove
            };

            return factory;

            function loadAll() {
                var deferred = $q.defer();
                $http.get(urls.LANCAMENTO_SERVICE_API)
                    .then(
                        function (response) {
                            $sessionStorage.lancamentos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
                        
            
            function getTiposLancamento(ok, error) {            	
            	if($sessionStorage.tiposLancamento){
            		ok($sessionStorage.tiposLancamento);
            		return;
            	}
                var deferred = $q.defer();                
                $http.get(urls.LANCAMENTO_SERVICE_API+"getTiposLancamento")
                    .then(
                        function (response) {                        	
                            $sessionStorage.tiposLancamento = response.data;
                            deferred.resolve(response);
                            if(ok)
                            	ok($sessionStorage.tiposLancamento);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                            if(error)
                            	error(errResponse);
                        }
                    );
                return deferred.promise;
            }
            

            function getAll(){
                return $sessionStorage.lancamentos;
            }

            function get(id) {
                var deferred = $q.defer();
                $http.get(urls.LANCAMENTO_SERVICE_API + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function create(obj) {                
                var deferred = $q.defer();
                $http.post(urls.LANCAMENTO_SERVICE_API, obj)
                    .then(
                        function (response) {                
                        	loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {                           
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function update(obj, id) {
                var deferred = $q.defer();
                $http.put(urls.LANCAMENTO_SERVICE_API + id, obj)
                    .then(
                        function (response) {
                        	loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function remove(id) {
                var deferred = $q.defer();
                $http.delete(urls.LANCAMENTO_SERVICE_API + id)
                    .then(
                        function (response) {
                        	loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);