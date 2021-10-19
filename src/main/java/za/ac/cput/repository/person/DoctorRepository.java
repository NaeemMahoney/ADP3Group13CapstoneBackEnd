package za.ac.cput.repository.person;
/*
 * Doctor Repository
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.entity.person.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, String> {
}
