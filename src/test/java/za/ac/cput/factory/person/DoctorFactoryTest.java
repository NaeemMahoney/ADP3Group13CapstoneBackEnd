package za.ac.cput.factory.person;

import org.junit.jupiter.api.Test;
import za.ac.cput.entity.person.Doctor;

import static org.junit.jupiter.api.Assertions.*;

class DoctorFactoryTest {

    @Test
    void createDoctor() {
        Doctor doctor = DoctorFactory.createDoctor("Mpumelelo", "Magagula", "mpumelelo@gmail.com","0840671155");
        System.out.println(doctor);
        }
}

