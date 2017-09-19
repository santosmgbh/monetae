package br.com.boleuti.monetae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Fluxo;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.repositories.FluxoRepository;
import br.com.boleuti.monetae.service.CentroCustoService;
import br.com.boleuti.monetae.service.LancamentoService;
import br.com.boleuti.monetae.service.UserService;
import br.com.boleuti.monetae.util.CustomErrorType;




@Controller
@RequestMapping("/lancamento")
public class LancamentoController {
	
		@Autowired
		LancamentoService lancamentoService;
		
		@Autowired
		CentroCustoService centroCustoService;
		
		@Autowired
		FluxoRepository fluxoRepository;

		@Autowired
		private UserService userService;
	   

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Lancamento>> listAll() {
			List<Lancamento> list = lancamentoService.findAll();
			if (list == null || list.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<Lancamento>>(list, HttpStatus.OK);
		}
		
		@RequestMapping(value = "{descricao}", method = RequestMethod.GET)
		public ResponseEntity<?> findByDescricao(@PathVariable("descricao") String nome) {
			Lancamento obj = lancamentoService.findByDescricao(nome);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("N�o encontrado com o nome "+nome), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Lancamento>(obj, HttpStatus.OK);
		}



		@RequestMapping(value = "{id}", method = RequestMethod.GET)
		public ResponseEntity<?> get(@PathVariable("id") long id) {
			Lancamento obj = lancamentoService.findOne(id);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Lancamento>(obj, HttpStatus.OK);
		}

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<?> create(@RequestBody Lancamento obj, UriComponentsBuilder ucBuilder) {

			if (obj.getId() != null && lancamentoService.exists(obj.getId())) {
				return new ResponseEntity(
						new CustomErrorType("Unable to create. A User with name " + obj.getDescricao() + " already exist."),
						HttpStatus.CONFLICT);
			}			
			obj.setUser(userService.getUsuarioLogado());
			Fluxo fluxo = fluxoRepository.findOne(obj.getFluxo().getId());
			CentroCusto centroCusto = centroCustoService.findOne(obj.getCentroCusto().getId());
			obj.setFluxo(fluxo);
			obj.setCentroCusto(centroCusto);
			lancamentoService.save(obj);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("Lancamento/{id}").buildAndExpand(obj.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

		@RequestMapping(value = "{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Lancamento user) {

			Lancamento obj = lancamentoService.findOne(id);

			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			obj.setUser(userService.getUsuarioLogado());
			obj.setDescricao(user.getDescricao());
			obj.setCentroCusto(user.getCentroCusto());
			obj.setFluxo(user.getFluxo());
			obj.setValor(user.getValor());

			lancamentoService.save(obj);
			return new ResponseEntity<Lancamento>(obj, HttpStatus.OK);
		}

		// ------------------- Delete a
		// -----------------------------------------

		@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> delete(@PathVariable("id") long id) {

			Lancamento obj = lancamentoService.findOne(id);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			lancamentoService.delete(id);
			return new ResponseEntity<Lancamento>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Delete All -----------------------------

		@RequestMapping(method = RequestMethod.DELETE)
		public ResponseEntity<Lancamento> deleteAll() {

			lancamentoService.deleteAll();
			return new ResponseEntity<Lancamento>(HttpStatus.NO_CONTENT);
		}

       
}
