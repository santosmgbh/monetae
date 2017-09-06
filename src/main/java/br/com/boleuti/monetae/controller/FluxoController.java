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

import br.com.boleuti.monetae.model.Fluxo;
import br.com.boleuti.monetae.repositories.FluxoRepository;
import br.com.boleuti.monetae.util.CustomErrorType;

@Controller
@RequestMapping("/fluxo")
public class FluxoController {

	private FluxoRepository fluxoRepository;

	@Autowired
	public FluxoController(FluxoRepository fluxoRepository) {
		this.fluxoRepository = fluxoRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Fluxo>> listAll() {
		List<Fluxo> list = fluxoRepository.findAll();
		if (list == null || list.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Fluxo>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Fluxo obj = fluxoRepository.findOne(id);
		if (obj == null) {
			return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Fluxo>(obj, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Fluxo fluxo, UriComponentsBuilder ucBuilder) {

		if (fluxoRepository.exists(fluxo.getId())) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A User with name " + fluxo.getNome() + " already exist."),
					HttpStatus.CONFLICT);
		}
		fluxoRepository.save(fluxo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("user/{id}").buildAndExpand(fluxo.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Fluxo user) {

		Fluxo obj = fluxoRepository.findOne(id);

		if (obj == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		obj.setNome(user.getNome());
		obj.setTipoFluxo(user.getTipoFluxo());

		fluxoRepository.save(obj);
		return new ResponseEntity<Fluxo>(obj, HttpStatus.OK);
	}

	// ------------------- Delete a
	// -----------------------------------------

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {

		Fluxo fluxo = fluxoRepository.findOne(id);
		if (fluxo == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		fluxoRepository.delete(id);
		return new ResponseEntity<Fluxo>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All -----------------------------

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Fluxo> deleteAll() {

		fluxoRepository.deleteAll();
		return new ResponseEntity<Fluxo>(HttpStatus.NO_CONTENT);
	}

}
