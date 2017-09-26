'use strict';

app.controller('EstatisticaController',
    ['EstatisticaService', '$scope','$location','$timeout',   function( EstatisticaService,  $scope, $location, $timeout) {

        var ng = $scope;        

        
        ng.init = function(){   
        	console.log("start");
        	ng.startLineChart();
        }
        
        ng.startLineChart = function(){        
        	ng.addSerie(document.getElementById('myChart').getContext('2d'));
        }
        
        ng.addSerie = function(ctx){
        	EstatisticaService.getLineChartLancamentos(function(result){
        		console.log(result);
        	});
        	var myChart = new Chart(ctx, {
        	    type: 'line',
        	    data: {
        	        labels: ["Jun/2017", "Jul/2017", "Ago/2017", "Set/2017", "Out/2017", "Nov/2017"],
        	        datasets: [{
        	            label: 'Entradas',
        	            data: [12, 19, 3, 5, 2, 3],        	            
        	            borderWidth: 2,
        	            lineTension: 0,
        	            borderColor: 'green',
        	            fill: false
        	        }, {
        	            label: 'Saídas',
        	            data: [23, 15, 5, 3, 1, 6],        	            
        	            borderWidth: 2,
        	            lineTension: 0,
        	            borderColor: 'red',
        	            fill: false
        	        }, {
        	            label: 'Saldo',
        	            data: [15, 20, 1, 5, 9, 13],        	            
        	            borderWidth: 2,
        	            lineTension: 0,
        	            borderColor: 'blue',
        	            fill: false
        	        }]
        	    }
        	,
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        }
        
        ng.init();
    }


    ]);