package za.ac.cput.services.person;
/*
 * Doctor Services
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.person.Doctor;
import za.ac.cput.repository.person.DoctorRepository;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService implements IDoctorService {

    private static DoctorService service = null;

    @Autowired
    private DoctorRepository repository;


    @Override
    public Doctor create(Doctor doctor) {

        return this.repository.save(doctor);
    }

    @Override
    public Doctor read(String id) {

        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Doctor update(Doctor doctor) {

        if (this.repository.existsById(doctor.getID()))
            return this.repository.save(doctor);
        return null;
    }

    @Override
    public boolean delete(String id) {
        this.repository.deleteById(id);
        if(this.repository.existsById(id))
            return false;
        else
        return true;
    }


    @Override
    public Set<Doctor> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    public Set<Doctor> getAllDoctorsStartWithA(){
        Set<Doctor> doctorsWithA = new HashSet<>();
        Set<Doctor> doctors = getAll();
        for (Doctor doctor : doctors){
            if (doctor.getID().trim().toLowerCase().startsWith("a")){
                doctorsWithA.add(doctor);
            }
        }
        return doctorsWithA;
    }

}
