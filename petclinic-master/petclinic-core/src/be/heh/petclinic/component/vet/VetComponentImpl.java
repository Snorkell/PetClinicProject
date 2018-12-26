package be.heh.petclinic.component.vet;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import be.heh.petclinic.component.database.*;
import javax.sql.DataSource;

import be.heh.petclinic.domain.*;

class VetComponentImpl implements VetComponent {

    private JdbcDao vetDao;
  
    public VetComponentImpl(DataSource dataSource){
        vetDao = new JdbcDao(dataSource);
    }
    @Override
    public void updateToDB(Vet vet) {
        vetDao.updateVetToDB(vet);
    }
    @Override
    public Collection<Vet> getVets() {
        List<Vet> vets = cleanList(vetDao.getVetData());
        return vets;
    }
    @Override
    public void deleteFomDB(Vet vet) {
        vetDao.deleteVetFromDB(vet);
    }
    @Override
    public Collection<Vet> getVetsByKey(String key, Object value){
        List<Vet> vets = cleanList(vetDao.getVetData());
        List<Vet> findVet = new ArrayList<Vet>();
        switch(key){
            case "id":
                for (Vet var : vets) {
                    if(var.getId() == Integer.parseInt((String)value)){
                        findVet.add(var);
                    }
                }
            break;
            case "firstName":
                for (Vet var : vets) {
                    if(var.getFirstname().equals((String)value)){
                        findVet.add(var);
                    }
                }
            break;
            case "lastName":
                for (Vet var : vets) {
                    if(var.getLastname().equals((String)value)){
                        findVet.add(var);
                    }
                }
            break;
            case "specialty": 
                for (Vet var : vets) { 
                    for (String specialty : var.getSpecialty()) {
                        if(specialty.equals((String)value)){
                            findVet.add(var);
                        }
                    }          
                }
            break;
            default:
                return null;
        }
        return findVet; 
    }

    private List<Vet> cleanList(List<Vet> vets){
        ArrayList<Vet> indexes = new ArrayList<>();
        for (Vet var : vets) {
            ArrayList<Vet> duplicatedVet = new ArrayList<>();
            for (Vet vet : vets) {
                if(var.getFirstname().equals(vet.getFirstname())&&var.getLastname().equals(vet.getLastname())){
                    duplicatedVet.add(vet);
                }
            }
            duplicatedVet.remove(0);
            for (Vet v : duplicatedVet) {
                var.addSpecialty(v.getSpecialty().get(0));
                indexes.add(v);
            }
        }
        vets.removeAll(indexes);
       return vets;
    }

    @Override
    public void saveToDB(Vet vet){
        vetDao.saveVetToDB(vet);
    }
}