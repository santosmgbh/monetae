'use strict';

app.controller('EstatisticaController',
    ['EstatisticaService', '$scope','$location','$timeout',   function( EstatisticaService,  $scope, $location, $timeout) {

        var ng = $scope;        

        
        ng.init = function(){   
        	console.log("start");
        	ng.startLineChart();
        }
        
        ng.startLineChart = function(){        
        	var ctx = document.getElementById('lineChartLancamentos').getContext('2d');
        	var labelSeries = [];
        	var dataSets = [];
        	EstatisticaService.getLineChartLancamentos(Date.parse('2017-01'), Date.parse('2017-12'), function(result){   
        		debugger
        		var labels = result.seriesLabel;
        		var series = result.series;
        		if(series){
        			for(var i in labels){
        				labelSeries.push(labels[i]);	
        			}
        			var datasets = [];
        			for(var i in series){
        				var dataSet = {
                	            label: series[i].title,
                	            data: series[i].valores,        	            
                	            borderWidth: 2,
                	            lineTension: 0,
                	            borderColor: series[i].color,
                	            fill: false
                	        };	
        				datasets.push(dataSet);
        			}
        			
        			var myChart = new Chart(ctx, {
                	    type: 'line',
                	    data: {
                	        labels: labelSeries,
                	        datasets: datasets
                	    }
                	,
                	    options: {
                	        scales: {
                	            yAxes: [{
                	                ticks: {
                	                    beginAtZero:false
                	                }
                	            }]
                	        }
                	    }
                	});
        			
        		}
        			
        	}, function(error){
        		
        	});        	
        }
        
        ng.init();
    }


    ]);