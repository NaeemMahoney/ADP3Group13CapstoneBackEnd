package za.ac.cput.controller.person;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.entity.general.Address;
import za.ac.cput.entity.medication.Item;
import za.ac.cput.entity.person.Patient;
import za.ac.cput.factory.general.AddressFactory;
import za.ac.cput.factory.person.PatientFactory;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerTest {

    Address address1 = AddressFactory.build("20669","Koyini",
            "Nomzamo","7140");
    private Patient patient1 = PatientFactory.build("Sipho","Manisi",
            address1.getAddressNumber(),"0830765321");
    Address address2 = AddressFactory.build("20679","Koyini",
            "Nomzamo","7140");
    private Patient patient2 = PatientFactory.build("Vuyo","Thwala",
            address2.getAddressNumber(),"0730925421");
    Address address3 = AddressFactory.build("29","Milton Street",
            "Strand","7140");
    private Patient patient3 = PatientFactory.build("Johnny","Bravo",
            address3.getAddressNumber(),"0633568021");
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String patientURL = "http://localhost:8080/patient";

    private String username = "user";
    private String password = "password";
    @Test
    void a_createPatientOne() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntityCreate1 = new HttpEntity<>(patient1, httpHeaders);
        ResponseEntity<Patient> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                httpEntityCreate1, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient1 = responseEntity.getBody();
        System.out.println("New Patient: "+patient1);
        assertEquals(patient1.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }
    @Test
    void b_createPatientTwo() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntityCreate2 = new HttpEntity<>(patient2, httpHeaders);
        ResponseEntity<Patient> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                httpEntityCreate2, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient2 = responseEntity.getBody();
        System.out.println("New Patient: "+patient2);
        assertEquals(patient2.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }
    @Test
    void c_createPatientThree() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntityCreate3 = new HttpEntity<>(patient3, httpHeaders);
        ResponseEntity<Patient> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                httpEntityCreate3, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient3 = responseEntity.getBody();
        System.out.println("New Patient: "+patient3);
        assertEquals(patient3.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }

    @Test
    void d_read() {
        String url = patientURL + "/read/" +patient2.getPatientNumber();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityRead = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<Patient> responseCreate = restTemplate.postForEntity(url, httpEntityRead, Patient.class);
        assertEquals(patient2.getPatientNumber(), responseCreate.getBody().getPatientNumber());
    }

    @Test
    void e_update() {
        Patient updatedPatient = new Patient.Builder().copy(patient1).setFirstName("siphosethu").build();
        String url = patientURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntityUpdate = new HttpEntity<>(updatedPatient, httpHeaders);
        System.out.println("Updated Patient: "+ updatedPatient);
        ResponseEntity<Patient> responseUpdate = restTemplate.exchange(url, HttpMethod.POST, httpEntityUpdate, Patient.class);
        assertNotNull(responseUpdate.getBody());
    }

    @Test
    void f_delete() {
        String url = patientURL + "/delete/" + patient1.getPatientNumber();
        restTemplate.delete(url);
    }

    @Test
    void g_getPatients() {
        String url = patientURL + "/getall";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, header);
        ResponseEntity<String> responseGetAll = restTemplate.withBasicAuth(username, password).exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("All patients are:\n"+responseGetAll.getBody());
    }
}