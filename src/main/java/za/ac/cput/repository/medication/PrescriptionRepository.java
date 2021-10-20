package za.ac.cput.repository.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.medication.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

}
