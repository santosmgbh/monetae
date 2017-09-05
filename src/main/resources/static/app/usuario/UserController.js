'use strict';

app.controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {

        var ng = this;
        ng.user = {};
        ng.users=[];

        ng.submit = submit;
        ng.getAllUsers = getAllUsers;
        ng.create = create;
        ng.updateUser = updateUser;
        ng.removeUser = removeUser;
        ng.editUser = editUser;
        ng.reset = reset;

        ng.successMessage = '';
        ng.errorMessage = '';
        ng.done = false;

        ng.onlyIntegers = /^\d+$/;
        ng.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (ng.user.id === undefined || ng.user.id === null) {
                console.log('Saving New User', ng.user);
                create(ng.user);
            } else {
                updateUser(ng.user, ng.user.id);
                console.log('User updated with id ', ng.user.id);
            }
        }

        function create(user) {
            console.log('About to create user');
            UserService.create(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        ng.successMessage = 'User created successfully';
                        ng.errorMessage='';
                        ng.done = true;
                        ng.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        ng.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        ng.successMessage='';
                    }
                );
        }


        function updateUser(user, id){
            console.log('About to update user');
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        ng.successMessage='User updated successfully';
                        ng.errorMessage='';
                        ng.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        ng.errorMessage='Error while updating User '+errResponse.data;
                        ng.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('About to remove User with id '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id) {
            ng.successMessage='';
            ng.errorMessage='';
            UserService.getUser(id).then(
                function (user) {
                    ng.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            ng.successMessage='';
            ng.errorMessage='';
            ng.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);