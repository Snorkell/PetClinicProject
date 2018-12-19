package be.heh.petclinic.component.pet;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import be.heh.petclinic.domain.*;
import be.heh.petclinic.component.database.*;

class PetComponentImpl implements PetComponent{
    private JdbcDao petDao;

    public PetComponentImpl(DataSource dataSource){
        petDao = new JdbcDao(dataSource);
    }
    @Override
    public void updateToDB(Pet pet) {
        petDao.updatePetToDB(pet);
    }
    @Override
    public void saveToDB(Pet pet) {
        petDao.savePetToDB(pet);
    }
    @Override
    public void deleteFromDB(Pet pet) {
        petDao.deletePetFromDB(pet);
    }
    @Override
    public Collection<Pet> getPets(){
        List<Pet> pets = petDao.getPetData();
        return pets;
    }
    @Override
    public Collection<Pet> getPetsByKey(String key, Object value){
        List<Pet> pets = petDao.getPetData();
        List<Pet> findPet = new ArrayList<Pet>();
        switch(key){
            case "id":
                for (Pet var : pets) {
                    if(var.getId() == Integer.parseInt((String)value)){
                        findPet.add(var);
                    }
                }
            break;
            case "name":
                for (Pet var : pets) {
                    if(var.getName().equals((String)value)){
                        findPet.add(var);
                    }
                }
            break;
            case "type":
                for (Pet var : pets) {
                    if(var.getType().equals((String)value)){
                        findPet.add(var);
                    }
                }
            break;
            case "owner":
                for (Pet var : pets) {
                    try{
                        int id = Integer.parseInt((String)value);
                        if(var.getOwner().getId() == id){
                            findPet.add(var);
                        }
                    }catch(Exception e){
                        if(var.getOwner().getFirstName().equals((String)value) || var.getOwner().getLastName().equals((String)value)){
                            findPet.add(var);
                        }
                    }
                    
                }
            break;
            default:
                return null;
        }
        return findPet;
    }
}