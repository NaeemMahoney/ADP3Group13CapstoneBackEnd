package za.ac.cput.services.medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.medication.Prescription;
import za.ac.cput.repository.medication.PrescriptionRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionService implements iPrescriptionService {
    private static PrescriptionService service = null;

    @Autowired
    private PrescriptionRepository repository;

    @Override
    public Prescription create(Prescription prescription) {return this.repository.save(prescription);}

    @Override
    public Prescription read(String prescriptionId) {
        return this.repository.findById(prescriptionId).orElse(null);
    }

    @Override
    public Prescription update(Prescription prescription) {
        if (this.repository.existsById(prescription.getPrescriptionID()))
            return this.repository.save(prescription);
        return null;
    }

    @Override
    public boolean delete(String prescriptionID) {
        this.repository.deleteById(prescriptionID);
        if (this.repository.existsById(prescriptionID)) {
            System.out.println("Prescription:" + prescriptionID + "unfortunately this is not deleted");
            return false;
        } else {
            System.out.println("Prescription is being Deleted:");
            return true;
        }
    }

    @Override
    public Set<Prescription> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    public Set<Prescription> getAllPrescriptionStartsWithP() {
        Set<Prescription> prescriptionsWithP = new HashSet<Prescription>();
        Set<Prescription> prescriptions = getAll();
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientNumber().trim().toLowerCase().startsWith("p")) {
                prescriptionsWithP.add(prescription);
            }
        }
        return prescriptionsWithP;
    }

    public Prescription getPrescriptionGivenName(String name) {
        Prescription p = null;
        Set<Prescription> prescriptions = getAll();
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientNumber().equals(name)) {
                p = prescription;
                break;
            }
        }
        return p;

    }

}




