'use strict';

app.factory('CentroCustoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAll: loadAll,
                getAll: getAll,
                get: get,
                create: create,
                update: update,
                remove: remove
            };

            return factory;

            function loadAll() {
                var deferred = $q.defer();
                $http.get(urls.CENTROCUSTO_SERVICE_API)
                    .then(
                        function (response) {
                            $localStorage.data = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAll(){
                return $localStorage.data;
            }

            function get(id) {
                var deferred = $q.defer();
                $http.get(urls.CENTROCUSTO_SERVICE_API + id)
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
                $http.post(urls.CENTROCUSTO_SERVICE_API, obj)
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
                $http.put(urls.CENTROCUSTO_SERVICE_API + id, obj)
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
                $http.delete(urls.CENTROCUSTO_SERVICE_API + id)
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