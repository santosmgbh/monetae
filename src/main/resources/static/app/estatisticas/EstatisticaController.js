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
        	var labelSeries = [];
        	var dataSets = [];
        	EstatisticaService.getLineChartLancamentos(function(result){        		
        		if(result.series){
        			for(i in result.series[0].itens){
        				labelSeries.push(result.series[0].itens[i]);	
        			}
        			
        			var dataSet = {
        	            label: 'Entradas',
        	            data: [12, 19, 3, 5, 2, 3],        	            
        	            borderWidth: 2,
        	            lineTension: 0,
        	            borderColor: 'green',
        	            fill: false
        	        };
        			
        		}
        			
        		console.log(result);
        	});
        	var myChart = new Chart(ctx, {
        	    type: 'line',
        	    data: {
        	        labels: labelSeries,
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