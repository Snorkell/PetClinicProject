package be.heh.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import be.heh.petclinic.component.vet.VetComponent;
import be.heh.petclinic.domain.Vet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("api/vets")
public class VetRestController {

	@Autowired
	private VetComponent vetComponentImpl;
    
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Vet>> getVets(){
	
		Collection<Vet> vets = vetComponentImpl.getVets();
		if(vets.isEmpty()){
			return new ResponseEntity<Collection<Vet>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Vet>>(vets,HttpStatus.OK);
	}
	@RequestMapping(value="/{key}/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Vet>> getVetsByKey(@PathVariable("key") String key, @PathVariable("value") Object value){
	
		Collection<Vet> vets = vetComponentImpl.getVetsByKey(key, value);
		if(vets.isEmpty()){
			return new ResponseEntity<Collection<Vet>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Vet>>(vets,HttpStatus.OK);
	}
	@RequestMapping(value="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vet> addVet(@RequestBody Vet vet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (vet == null)){
			return new ResponseEntity<Vet>(HttpStatus.BAD_REQUEST);
		}
		vetComponentImpl.saveToDB(vet);
		return new ResponseEntity<Vet>(vet, HttpStatus.CREATED);
	}
	@RequestMapping(value="update/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vet> UpdateVet(@RequestBody Vet vet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (vet == null)){
			return new ResponseEntity<Vet>(HttpStatus.BAD_REQUEST);
		}
		vetComponentImpl.updateToDB(vet);
		return new ResponseEntity<Vet>(vet, HttpStatus.OK);
	}
	@RequestMapping(value="delete/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vet> DeleteVet(@RequestBody Vet vet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (vet == null)){
			return new ResponseEntity<Vet>(HttpStatus.BAD_REQUEST);
		}
		vetComponentImpl.deleteFomDB(vet);
		return new ResponseEntity<Vet>(HttpStatus.OK);
	}
}