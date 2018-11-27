package be.heh.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import be.heh.petclinic.component.owner.OwnerComponent;
import be.heh.petclinic.domain.Owner;

import java.util.List;

import javax.validation.Valid;

import java.util.Collection;

@RestController
@RequestMapping("api/owners")
public class OwnerRestController {
	@Autowired
	private OwnerComponent OwnerComponentImpl;
    
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Owner>> getOwners(){
	
		Collection<Owner> owners = OwnerComponentImpl.getOwners();
		if(owners.isEmpty()){
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners,HttpStatus.OK);
	}
	@RequestMapping(value="/{key}/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Owner>> getOwnerByKey(@PathVariable("key") String key, @PathVariable("value") Object value){
	
		Collection<Owner> owners = OwnerComponentImpl.getOwnerByKey(key, value);
		if(owners == null){
			return new ResponseEntity<Collection<Owner>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Owner>>(owners,HttpStatus.OK);
	}
	@RequestMapping(value="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Owner> addOwner(@RequestBody Owner owner, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (owner == null)){
			return new ResponseEntity<Owner>(HttpStatus.BAD_REQUEST);
		}
		OwnerComponentImpl.saveToDb(owner);
		return new ResponseEntity<Owner>(owner, HttpStatus.CREATED);
	}
}