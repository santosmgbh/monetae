'use strict';

app.factory('FluxoService',
    ['$sessionStorage', '$http', '$q', 'urls',
        function ($sessionStorage, $http, $q, urls) {

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
                $http.get(urls.FLUXO_SERVICE_API)
                    .then(
                        function (response) {
                            $sessionStorage.fluxos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAll(){
                return $sessionStorage.fluxos;
            }

            function get(id) {
                var deferred = $q.defer();
                $http.get(urls.FLUXO_SERVICE_API + id)
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
                $http.post(urls.FLUXO_SERVICE_API, obj)
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
                $http.put(urls.FLUXO_SERVICE_API + id, obj)
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
                $http.delete(urls.FLUXO_SERVICE_API + id)
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