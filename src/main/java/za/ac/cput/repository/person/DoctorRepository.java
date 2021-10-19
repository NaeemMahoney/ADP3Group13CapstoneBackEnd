package za.ac.cput.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.person.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, String> {
}
