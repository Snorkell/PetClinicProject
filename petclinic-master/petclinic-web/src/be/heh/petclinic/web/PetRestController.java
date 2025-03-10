package be.heh.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import be.heh.petclinic.component.pet.PetComponent;
import be.heh.petclinic.domain.Pet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("api/pets")
public class PetRestController {

	@Autowired
	private PetComponent PetComponentImpl;
    
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Pet>> getPets(){
	
		Collection<Pet> pets = PetComponentImpl.getPets();
		if(pets.isEmpty()){
			return new ResponseEntity<Collection<Pet>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Pet>>(pets,HttpStatus.OK);
	}
	@RequestMapping(value="/{key}/{value}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Pet>> getPetsByKey(@PathVariable("key") String key, @PathVariable("value") Object value){
	
		Collection<Pet> pets = PetComponentImpl.getPetsByKey(key, value);
		if(pets.isEmpty()){
			return new ResponseEntity<Collection<Pet>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Pet>>(pets,HttpStatus.OK);
	}
	@RequestMapping(value="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (pet == null)){
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}
		PetComponentImpl.saveToDB(pet);
		return new ResponseEntity<Pet>(pet, HttpStatus.CREATED);
	}
	@RequestMapping(value="update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pet> UpdatePet(@RequestBody Pet pet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (pet == null)){
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}
		PetComponentImpl.updateToDB(pet);
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}
	@RequestMapping(value="delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pet> DeletePet(@RequestBody Pet pet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (pet == null)){
			return new ResponseEntity<Pet>(HttpStatus.BAD_REQUEST);
		}
		PetComponentImpl.deleteFromDB(pet);
		return new ResponseEntity<Pet>(HttpStatus.OK);
	}
}