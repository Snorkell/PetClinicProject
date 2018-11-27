package be.heh.petclinic.component.owner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import be.heh.petclinic.domain.*;
import be.heh.petclinic.component.database.*;

class OwnerComponentImpl implements OwnerComponent{
    private JdbcDao ownerDao;

    public OwnerComponentImpl(DataSource dataSource){
        ownerDao = new JdbcDao(dataSource);
    }
    @Override
    public void updateOwnerToDB(Owner owner) {
        ownerDao.updateOwnerToDB(owner);
    }
    @Override
    public void saveToDb(Owner owner) {
        ownerDao.saveOwnerToDB(owner);
    }
    @Override
    public Collection<Owner> getOwners(){
        List<Owner> owners = ownerDao.getOwnerData();
        return owners;
    }
    @Override
    public Collection<Owner> getOwnerByKey(String key, Object value){
        List<Owner> owners = ownerDao.getOwnerData();
        List<Owner> findOwner = new ArrayList<Owner>();
        if(!key.isEmpty()){
            switch(key){
                case "id":
                    for (Owner var : owners) {
                        if(var.getId() == Integer.parseInt((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                case "firstName":
                    for (Owner var : owners) {
                        if(var.getFirstName().equals((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                case "lastName":
                    for (Owner var : owners) {
                        if(var.getLastName().equals((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                case "address":
                    for (Owner var : owners) {
                        if(var.getAddress().equals((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                case "city":
                    for (Owner var : owners) {
                        if(var.getCity().equals((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                case "telephone":
                    for (Owner var : owners) {
                        if(var.getTelephone().equals((String)value)){
                            findOwner.add(var);
                        }
                    }
                break;
                default :
                    return null;
            }
        }
        return findOwner;
    }
    
}
