package za.ac.cput.repository.general;
/* iAppointmentRepository.java
   Interface for Appointment Repository
   Author: Nolusindiso Makosa (219023557)
   Due Date: 20 October 2021
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.general.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
