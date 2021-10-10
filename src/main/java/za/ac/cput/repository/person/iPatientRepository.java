package za.ac.cput.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.person.Patient;

@Repository
public interface iPatientRepository extends JpaRepository<Patient,String> {

}
