package za.ac.cput.services.person;

import za.ac.cput.entity.person.Patient;
import za.ac.cput.services.iService;

import java.util.Set;

public interface iPatientServices extends iService<Patient,String> {
    public Set<Patient> getAll();
}
