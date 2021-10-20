package za.ac.cput.services.person;
/*
 * Doctor Entity
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.entity.person.Doctor;
import za.ac.cput.factory.person.DoctorFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class DoctorServiceTest {
    @Autowired
    private  DoctorService service;

    private  static Doctor doctor = DoctorFactory.createDoctor("Mandla", "Ndlala", "drndlala@gmail.com","08602365689");

    @Test
    void create() {
        Doctor created = service.create(doctor);
        assertEquals(created.getID(), doctor.getID());
        System.out.println("Create: "+ created );
    }

    @Test
    void read() {
        Doctor read = service.read(doctor.getID());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    @Deprecated
    void update() {
        Doctor old = service.read("74555a60-6873-4ef7-b613-a3b044331e6f");
//        Doctor updated = Doctor.Builder.copy(old).setFirstName("Mandla").setLastName("Ndlala").setEmailAddress("drndlala@gmail.com").setContactNumber("08602365689").Build();
//        assertNotNull(service.update(updated));
//        System.out.println("Update: " + updated);
    }

    @Test
    void delete() {
        boolean success = service.delete("74555a60-6873-4ef7-b613-a3b044331e6f");
        assertTrue(success);
        System.out.println("Delete: " + success);
    }

    @Test
    void getAll() {
        System.out.println("Show all: ");
        System.out.println(service.getAll());
    }
}