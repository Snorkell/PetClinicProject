package be.heh.petclinic.component.visit;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import java.util.Date;
import java.text.SimpleDateFormat;
import be.heh.petclinic.domain.*;
import be.heh.petclinic.component.database.*;

class VisitComponentImpl implements VisitComponent{
    private JdbcDao visitDao;

    public VisitComponentImpl(DataSource dataSource){
        visitDao = new JdbcDao(dataSource);
    }
    @Override
    public void updateToDB(Visit visit) {
        visitDao.updateVisitToDB(visit);
    }
    @Override
    public void saveToDB(Visit visit) {
        visitDao.saveVisitToDB(visit);
    }
    @Override
    public void deleteFomDB(Visit visit) {
        visitDao.deleteVisitsFromDB(visit);
    }
    @Override
    public Collection<Visit> getVisits(){
        List<Visit> visits = visitDao.getVisitData();
        return visits;
    }
    @Override
    public Collection<Visit> getVisitsByKey(String key, Object value){
        List<Visit> visits = visitDao.getVisitData();
        List<Visit> findVisit = new ArrayList<Visit>();
        switch(key){
            case "id":
                for (Visit var : visits) {
                    if(var.getId() == Integer.parseInt((String)value)){
                        findVisit.add(var);
                    }
                }
            break;
            case "pet":
                for (Visit var : visits) {
                    try{
                        if(var.getPet().getType().equals((String)value)){
                            findVisit.add(var);
                        }
                    }catch(Exception e){
                        if(var.getPet().getName().equals((String)value)||var.getPet().getId() == (Integer.parseInt((String)value))){
                            findVisit.add(var);
                        }
                    }
                }
            break;
            case "date":
                for (Visit var : visits) {
                    if(var.getDate().equals((String)value)){
                        findVisit.add(var);
                    }
                }
            break;
            case "owner":
                for (Visit var : visits) {
                    try{
                        if(var.getPet().getOwner().getId() == Integer.parseInt((String)value)){
                            findVisit.add(var);
                        }
                    }catch(Exception e){
                        if(var.getPet().getOwner().getFirstName().equals((String)value) || var.getPet().getOwner().getLastName().equals((String)value) || var.getPet().getOwner().getAddress().contains((String)value) || var.getPet().getOwner().getCity().equals((String)value)|| var.getPet().getOwner().getTelephone().equals((String)value)){
                            findVisit.add(var);
                        }
                    }                    
                }
            break;
            default:
                return null;
        }
        return findVisit;
    }
}