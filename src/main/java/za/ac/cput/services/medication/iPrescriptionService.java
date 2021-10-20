package za.ac.cput.services.medication;

import za.ac.cput.entity.medication.Prescription;
import za.ac.cput.services.iService;

import java.util.Set;

public interface iPrescriptionService  extends iService<Prescription, String> {
    public Set<Prescription> getAll();
    public Prescription getPrescriptionGivenName(String name);
}
