'use strict';

app.controller('EstatisticaController',
    ['EstatisticaService', '$scope','$location','$timeout',   function( EstatisticaService,  $scope, $location, $timeout) {

        var ng = $scope;        

        
        ng.init = function(){   
        	console.log("start");
        	ng.dataIni = new Date(Date.parse('2017-01'));
        	ng.dataFim = new Date(Date.parse('2017-12'));        	
        	ng.loadLineChart();
        }
        
        ng.loadLineChart = function(){        
        	var ctx = document.getElementById('lineChartLancamentos').getContext('2d');
        	var labelSeries = [];
        	var dataSets = [];        	                	        	
        	
        	var dataIni = Date.parse(ng.dataIni);
        	var dataFim =  Date.parse(ng.dataFim);        	        	
        	
        	EstatisticaService.getLineChartLancamentos(dataIni, dataFim, function(result){   
        		console.log(result);
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
        
        ng.formatDate = function(date){
        	var d = date.getDate();
        	var m =  date.getMonth();
        	m += 1;  // JavaScript months are 0-11
        	var y = date.getFullYear();
        	return m+'-'+d+'-'+y
        }
        
        ng.init();
               
    }       


    ]);