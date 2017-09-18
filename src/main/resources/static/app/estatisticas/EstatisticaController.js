'use strict';

app.controller('EstatisticaController',
    ['EstatisticaService', '$scope','$location','$timeout',   function( EstatisticaService,  $scope, $location, $timeout) {

        var ng = $scope;        

        
        ng.init = function(){   
        	console.log("start");
        	ng.startChart();
        }
        
        ng.startChart = function(){        	
        	  
        }
        
        ng.addSerie = function(component, serie){
        	var barOptions = {
      		        series: {
      		            lines: {
      		                show: true,
      		                lineWidth: 2,
      		                fill: true,
      		                fillColor: {
      		                    colors: [{
      		                            opacity: 0.0
      		                        }, {
      		                            opacity: 0.0
      		                        }]
      		                }
      		            }
      		        },
      		        xaxis: {
      		            tickDecimals: 0
      		        },
      		        colors: ["#00b8ce"],
      		        grid: {
      		            color: "#999999",
      		            hoverable: true,
      		            clickable: true,
      		            tickColor: "#D4D4D4",
      		            borderWidth: 0
      		        },
      		        legend: {
      		            show: false
      		        },
      		        tooltip: true,
      		        tooltipOpts: {
      		            content: "x: %x, y: %y"
      		        }
      		    };
      		    var barData = {
      		        label: "bar",
      		        data: [
      		            [1, 34],
      		            [2, 25],
      		            [3, 19],
      		            [4, 34],
      		            [5, 32],
      		            [6, 44],
      		            [7, 50]
      		        ]
      		    };
      		    $.plot($("#flot-line-chart"), [barData, barData2], barOptions);
        }
        
        ng.init();
    }


    ]);