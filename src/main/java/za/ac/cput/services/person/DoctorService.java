package za.ac.cput.services.person;

import org.springframework.stereotype.Service;
import za.ac.cput.entity.person.Doctor;

import java.util.Set;

@Service
public class DoctorService implements IDoctorService {

    private static DoctorService service = null;
    private DoctorRepository repository = null;

    private DoctorService() {

        this.repository = DoctorRepository.getRepository();
    }

    public static DoctorService getService() {
        if (service == null) {
            service = new DoctorService();
        }
        return service;
    }
    @Override
    public Doctor create(Doctor doctor) {
        return repository.create(doctor);
    }

    @Override
    public Doctor read(String id) {
        return repository.read(id);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return repository.update(doctor);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    public Set<Doctor> getAll() {
        return (Set<Doctor>) this.repository.getAll();
    }

}
