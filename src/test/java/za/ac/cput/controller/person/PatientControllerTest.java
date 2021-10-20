package za.ac.cput.controller.person;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.entity.general.Address;
import za.ac.cput.entity.person.Patient;
import za.ac.cput.factory.general.AddressFactory;
import za.ac.cput.factory.person.PatientFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerTest {
    Address address1 = AddressFactory.build("20669","Koyini",
            "Nomzamo","7140");
    private Patient patient1 = PatientFactory.build("Sipho","Manisi",
            "0830765321",address1.getAddressNumber());
    Address address2 = AddressFactory.build("20679","Koyini",
            "Nomzamo","7140");
    private Patient patient2 = PatientFactory.build("Vuyo","Thwala",
            "0730925421",address2.getAddressNumber());
    Address address3 = AddressFactory.build("29","Milton Street",
            "Strand","7140");
    private Patient patient3 = PatientFactory.build("Johnny","Bravo",
            "0633568021",address3.getAddressNumber());
    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String patientURL = "http://localhost:8080/patient";

    private String username = "user";
    private String password = "password";
    @Test
    void createPatientOne() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntity = new HttpEntity<>(patient1, httpHeaders);
        ResponseEntity<Patient> responseEntity = testRestTemplate.postForEntity(url, httpEntity, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient1 = responseEntity.getBody();
        System.out.println("New Patient: "+patient1);
        assertEquals(patient1.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }
    @Test
    void createPatientTwo() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntity = new HttpEntity<>(patient2, httpHeaders);
        ResponseEntity<Patient> responseEntity = testRestTemplate.postForEntity(url, httpEntity, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient2 = responseEntity.getBody();
        System.out.println("New Patient: "+patient2);
        assertEquals(patient2.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }
    @Test
    void createPatientThree() {
        String url = patientURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntity = new HttpEntity<>(patient3, httpHeaders);
        ResponseEntity<Patient> responseEntity = testRestTemplate.postForEntity(url, httpEntity, Patient.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        patient3 = responseEntity.getBody();
        System.out.println("New Patient: "+patient3);
        assertEquals(patient2.getPatientNumber(), responseEntity.getBody().getPatientNumber());
    }

    @Test
    void read() {
        String url = patientURL + "/read/" +patient1.getPatientNumber();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<Patient> responseCreate = testRestTemplate.postForEntity(url, request, Patient.class);
        assertEquals(patient1.getPatientNumber(), responseCreate.getBody().getPatientNumber());
    }

    @Test
    void update() {
        Patient updatedPatient = new Patient.Builder().copy(patient1).setFirstName("Siphosethu").build();
        String url = patientURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Patient> httpEntity = new HttpEntity<>(updatedPatient, httpHeaders);
        ResponseEntity<Patient> responseUpdate = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Patient.class);
        assertNotNull(responseUpdate.getBody());
    }

    @Test
    void delete() {
        String url = patientURL + "/delete/" + patient1.getPatientNumber();
        testRestTemplate.delete(url);
    }

    @Test
    void getPatients() {
        String url = patientURL + "/getall";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, header);
        ResponseEntity<String> responseGetAll = testRestTemplate.withBasicAuth(username, password).exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("All patients are:\n"+responseGetAll.getBody());
    }
}