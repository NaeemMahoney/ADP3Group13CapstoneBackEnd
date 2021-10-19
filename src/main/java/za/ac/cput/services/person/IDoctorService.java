package za.ac.cput.services.person;

import za.ac.cput.entity.person.Doctor;

import java.util.Set;

public interface IDoctorService extends IService<Doctor, String>{
    public Set<Doctor> getAll();
}
