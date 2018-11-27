package be.heh.petclinic.component.owner;
import be.heh.petclinic.domain.Owner;
import java.util.Collection;

public interface OwnerComponent{
    Collection<Owner> getOwners();
    Collection<Owner> getOwnerByKey(String Key, Object value);
    void saveToDb(Owner owner);
    void updateOwnerToDB(Owner owner);
}
