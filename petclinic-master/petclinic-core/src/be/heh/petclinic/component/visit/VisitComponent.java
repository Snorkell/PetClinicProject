package be.heh.petclinic.component.visit;

import be.heh.petclinic.domain.Visit;

import java.util.List;

import java.util.Collection;

public interface VisitComponent{

    Collection<Visit> getVisits();
    Collection<Visit> getVisitsByKey(String key, Object value);
    void saveToDB(Visit visit);
}