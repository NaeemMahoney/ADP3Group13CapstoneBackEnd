package za.ac.cput.services.medication;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.entity.medication.Prescription;
import za.ac.cput.factory.medication.PrescriptionFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class PrescriptionServiceTest {
    @Autowired
    private PrescriptionService service;

    private static Prescription prescription = PrescriptionFactory.buildPrescription("Queen", "pn23", "24.5","12.6");

    @Test
    void a_create(){
        Prescription created = service.create(prescription);
        assertEquals(created.getPrescriptionID(), prescription.getPrescriptionID());
        System.out.println("created" + created);
    }

    @Test
    @Disabled
    void b_read() {
        Prescription read = service.read(prescription.getPrescriptionID());
        assertNotNull(read);
        System.out.println("read:" + read);
    }
  @Test
  @Disabled
    void c_update() {
       Prescription old = service.read("7f24388d-9696-4bba-88b8-77f57e299385");
       Prescription updated = new Prescription.Builder().copy(old).setName("parker").setPatientNumber("pn009").setBill("12.6").setDosage("54.9").build();
       assertNotNull(service.update(updated));
       System.out.println(" updated" +" "+ updated );
    }
    @Test
    @Disabled
    void e_delete() {
       boolean done = service.delete("108ff40a-9890-490e-aafb-b046ae17f809");
        assertTrue(done);
        System.out.println("successfully deleted!!" +  " " + done);
   }

   @Test
   @Disabled
   void d_getAll() {
       System.out.println("Showing All stuff");
       System.out.println(service.getAll());
    }
}

