package br.com.boleuti.monetae.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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
import br.com.boleuti.monetae.repositories.CentroCustoRepository;
import br.com.boleuti.monetae.util.CustomErrorType;




@Controller
@RequestMapping("/centroCusto")
public class CentroCustoController {
	
	   private CentroCustoRepository centroCustoRepository;
	   

		@Autowired
		public CentroCustoController(CentroCustoRepository centroCustoRepository) {
			this.centroCustoRepository = centroCustoRepository;
		}

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<CentroCusto>> listAll() {
			List<CentroCusto> list = centroCustoRepository.findAll();
			if (list == null || list.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<CentroCusto>>(list, HttpStatus.OK);
		}
		
		@RequestMapping(value = "{nome}", method = RequestMethod.GET)
		public ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
			CentroCusto obj = centroCustoRepository.findByNome(nome);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("N�o encontrado com o nome "+nome), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<CentroCusto>(obj, HttpStatus.OK);
		}



		@RequestMapping(value = "{id}", method = RequestMethod.GET)
		public ResponseEntity<?> get(@PathVariable("id") long id) {
			CentroCusto obj = centroCustoRepository.findOne(id);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<CentroCusto>(obj, HttpStatus.OK);
		}

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<?> create(@RequestBody CentroCusto obj, UriComponentsBuilder ucBuilder) {

			if (obj.getId() != null && centroCustoRepository.exists(obj.getId())) {
				return new ResponseEntity(
						new CustomErrorType("Unable to create. A User with name " + obj.getNome() + " already exist."),
						HttpStatus.CONFLICT);
			}
			centroCustoRepository.save(obj);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("centroCusto/{id}").buildAndExpand(obj.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

		@RequestMapping(value = "{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody CentroCusto user) {

			CentroCusto obj = centroCustoRepository.findOne(id);

			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}

			obj.setNome(user.getNome());							

			centroCustoRepository.save(obj);
			return new ResponseEntity<CentroCusto>(obj, HttpStatus.OK);
		}

		// ------------------- Delete a
		// -----------------------------------------

		@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> delete(@PathVariable("id") long id) {

			CentroCusto obj = centroCustoRepository.findOne(id);
			if (obj == null) {
				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			centroCustoRepository.delete(id);
			return new ResponseEntity<CentroCusto>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Delete All -----------------------------

		@RequestMapping(method = RequestMethod.DELETE)
		public ResponseEntity<CentroCusto> deleteAll() {

			centroCustoRepository.deleteAll();
			return new ResponseEntity<CentroCusto>(HttpStatus.NO_CONTENT);
		}

       
}
