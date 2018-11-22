package be.heh.petclinic.web;
import java.util.ArrayList;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import be.heh.petclinic.component.vet.VetComponent;
import be.heh.petclinic.component.owner.OwnerComponent;
import be.heh.petclinic.component.visit.VisitComponent;
import be.heh.petclinic.component.pet.PetComponent;
import be.heh.petclinic.domain.*;

import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class FullRestController{
    @Autowired
    private OwnerComponent OwnerComponentImpl;
    @Autowired
    private PetComponent PetComponentImpl;
    @Autowired
    private VetComponent vetComponentImpl;
    @Autowired
    private VisitComponent VisitComponentImpl;
    
    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Object>> getVisits(){
        Collection<Object> rawData = new ArrayList<Object>();
        Collection<Visit> visits = VisitComponentImpl.getVisits();
		Collection<Owner> owners = OwnerComponentImpl.getOwners();
		Collection<Pet> pets = PetComponentImpl.getPets();
        Collection<Vet> vets = vetComponentImpl.getVets();
        rawData.add(new String("Visits"));
        rawData.add(visits);
        rawData.add(new String("Owners"));
        rawData.add(owners);
        rawData.add(new String("Pets"));
        rawData.add(pets);
        rawData.add(new String("Vets"));
        rawData.add(vets);
		if(visits.isEmpty()){
			return new ResponseEntity<Collection<Object>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Object>>(rawData,HttpStatus.OK);
	}
}