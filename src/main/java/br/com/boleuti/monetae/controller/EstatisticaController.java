package br.com.boleuti.monetae.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.boleuti.monetae.model.LineChart;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.repositories.FluxoRepository;
import br.com.boleuti.monetae.repositories.TipoLancamentoRepository;
import br.com.boleuti.monetae.service.CentroCustoService;
import br.com.boleuti.monetae.service.LancamentoService;
import br.com.boleuti.monetae.service.UserService;




@Controller
@RequestMapping("/estatistica")
public class EstatisticaController {
	
	
		@Autowired
		LancamentoService lancamentoService;
		
	   

		@RequestMapping(method = RequestMethod.GET, path = "getLineChartLancamentos")
		public ResponseEntity<LineChart> getLineChartLancamentos(@PathVariable("dtIni") Date dtIni, @PathVariable("dtIni") Date dtFim) {
			LineChart lineChart = lancamentoService.getLineChartLancamentos(dtIni, dtFim);
			if (lineChart == null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<LineChart>(lineChart, HttpStatus.OK);
		}		

       
}
