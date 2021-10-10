package za.ac.cput.services.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.person.Patient;
import za.ac.cput.repository.person.PatientRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements iPatientService{
    private static PatientService patientService = null;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient){
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient read(String patientNumber){
        return this.patientRepository.findById(patientNumber).orElse(null);
    }

    @Override
    public Patient update(Patient patient){
        if (this.patientRepository.existsById(patient.getPatientNumber())){
            return this.patientRepository.save(patient);}
        return null;
    }

    @Override
    public boolean delete(String patientNumber){
        this.patientRepository.deleteById(patientNumber);
        if(this.patientRepository.existsById(patientNumber)){
            System.out.println("Patient: "+patientNumber+" was not deleted successfully");
            return false;
        }
        else{
            System.out.println("Patient: "+patientNumber+" successfully deleted");
            return true;
        }
    }

    @Override
    public Set<Patient> getAll(){
        return this.patientRepository.findAll().stream().collect(Collectors.toSet());
    }
}
