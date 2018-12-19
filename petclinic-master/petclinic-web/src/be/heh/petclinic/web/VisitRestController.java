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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import be.heh.petclinic.component.visit.VisitComponent;
import be.heh.petclinic.domain.Visit;

import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("api/visits")
public class VisitRestController {

	@Autowired
	private VisitComponent VisitComponentImpl;
    
	//@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Visit>> getVisits(){
	
		Collection<Visit> visits = VisitComponentImpl.getVisits();
		if(visits.isEmpty()){
			return new ResponseEntity<Collection<Visit>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Visit>>(visits,HttpStatus.OK);
	}

	@RequestMapping(value="/{key}/{value}",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Visit>> getVisitsByKey(@PathVariable("key")String key, @PathVariable("value")Object value){
		Collection<Visit> visits = VisitComponentImpl.getVisitsByKey(key, value);
		if(visits.isEmpty()){
			return new ResponseEntity<Collection<Visit>>(HttpStatus.NOT_FOUND);
		} 
		return new ResponseEntity<Collection<Visit>>(visits,HttpStatus.OK);
	}
	@RequestMapping(value="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Visit> addVisit(@RequestBody Visit visit, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (visit == null)){
			return new ResponseEntity<Visit>(HttpStatus.BAD_REQUEST);
		}
		VisitComponentImpl.saveToDB(visit);
		return new ResponseEntity<Visit>(visit, HttpStatus.CREATED);
	}
	@RequestMapping(value="update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Visit> UpdateVisit(@RequestBody Visit visit, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (visit == null)){
			return new ResponseEntity<Visit>(HttpStatus.BAD_REQUEST);
		}
		VisitComponentImpl.updateToDB(visit);
		return new ResponseEntity<Visit>(visit, HttpStatus.OK);
	}
	@RequestMapping(value="delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Visit> DeleteVisit(@RequestBody Visit visit, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (visit == null)){
			return new ResponseEntity<Visit>(HttpStatus.BAD_REQUEST);
		}
		VisitComponentImpl.deleteFomDB(visit);
		return new ResponseEntity<Visit>(HttpStatus.OK);
	}
}