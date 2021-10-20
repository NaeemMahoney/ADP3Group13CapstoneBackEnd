package za.ac.cput.controller.person;
/*
 * Doctor Entity
 * Name :Mpumelelo Magagula
 * Student Number:218136021
 * Description: This entity display all the records of doctors working in the clinic and their occupation level and status.
 * Date:November 2021
 * */
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.entity.medication.Item;
import za.ac.cput.entity.person.Doctor;
import za.ac.cput.factory.person.DoctorFactory;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DoctorControllerTest {

    private static Doctor doctor = DoctorFactory.createDoctor("Kane", "Hendriks","Drhendriks@gmail.com", "07102568945");
    public static String SECURITY_USERNAME = "user";
    public static String SECURITY_PASSWORD = "password";

    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/doctor";

    @Test
    void create() {
        String url = baseURL + "/create";
        ResponseEntity<Doctor> postResponse = restTemplate.postForEntity(url, doctor, Doctor.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        doctor= postResponse.getBody();
        System.out.println("Saved data: " + doctor);
        assertEquals(doctor.getID(), postResponse.getBody().getID());
    }

    @Test
    void read() {
        String url = baseURL + "/read/" + doctor.getID();
        System.out.println("URL: " + url);
        ResponseEntity<Doctor> responseCreate = restTemplate.getForEntity(url, Doctor.class);
        assertEquals(doctor.getID(), responseCreate.getBody().getID());
    }

    @Test
    void update() {
        Doctor updated = new Doctor.Builder().copy(doctor).setFirstName("Kane").setLastName("Hendriks").setEmailAddress("Drhendriks@gmail.com").setContactNumber("07102568945").Build();
        String url = baseURL + "/update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + updated);
        ResponseEntity<Doctor> response = restTemplate.postForEntity(url, updated, Doctor.class);
        assertNotNull(response.getBody());

    }

    @Test
    void delete() {
        String url = baseURL + "/delete/" + doctor.getID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    void getAll() {
        String url = baseURL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show All: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}