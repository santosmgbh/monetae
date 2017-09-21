package br.com.boleuti.monetae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.boleuti.monetae.model.vo.LancamentoVO;
import br.com.boleuti.monetae.repositories.LancamentoVORepository;




@Controller
@RequestMapping("/importacao")
public class ImportacaoController {
	
		@Autowired
		LancamentoVORepository LancamentoVORepository;
	   

		@RequestMapping(method = RequestMethod.POST, path = "importarLancamentos")		
		public ResponseEntity<?> importarLancamentos(@RequestBody List<LancamentoVO> lancamentos, UriComponentsBuilder ucBuilder) {
			for(LancamentoVO l: lancamentos){
				LancamentoVORepository.save(l);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("Lancamento/{id}").buildAndExpand(lancamentos.size()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}


       
}
