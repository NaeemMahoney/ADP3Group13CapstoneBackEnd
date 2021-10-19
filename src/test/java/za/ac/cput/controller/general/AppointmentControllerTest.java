package za.ac.cput.controller.general;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.entity.general.Appointment;
import za.ac.cput.factory.general.AppointmentFactory;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class AppointmentControllerTest {
    private static Appointment appointment = AppointmentFactory.build("20 November 2021", "14:30PM",
            "COVID Test", "P10NM", "D01MM", "R02LM");

    @Autowired
    private TestRestTemplate restTemplate;
    private final String baseURL = "http://localhost:8080/appointment";

    private String username = "user";
    private String password = "password";

    @Test
    void a_create() {
        String url = baseURL + "/create";
       ResponseEntity<Appointment> postResponse = restTemplate
               .withBasicAuth("user", "password")
               .postForEntity(url, appointment, Appointment.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
        appointment = postResponse.getBody();
        System.out.println("New added appointment: "+appointment);
        assertEquals(appointment.getAppointmentID(), postResponse.getBody().getAppointmentID());
    }

//    @Test
//    @Disabled
//    void b_create() {
//        String url = baseURL + "/create";
//        httpHeaders.setBasicAuth(username, password);
//        HttpEntity<Appointment> httpEntity = new HttpEntity<>(appointment1, httpHeaders);
//        ResponseEntity<Appointment> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Appointment.class);
//        assertNotNull(responseEntity);
//        assertNotNull(responseEntity.getBody());
//        appointment1 = responseEntity.getBody();
//        System.out.println("New added appointment: "+appointment1);
//        assertEquals(appointment1.getAppointmentID(), responseEntity.getBody().getAppointmentID());
//    }


    @Test
    void c_read() {
        String url = baseURL + "/read/" + appointment.getAppointmentID();
        System.out.println("URL: " + url);
        ResponseEntity<Appointment> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity(url, Appointment.class);
        assertEquals(appointment.getAppointmentID(), response.getBody().getAppointmentID());

    }

    @Test
    @Disabled
    void d_update() {
        Appointment update = new Appointment.Builder().copy(appointment).setDate("08 December 2021").setTime("13:00").setReason("Fetch Results")
                .setPatientNumber("P12MM").setDoctorNumber("D01NM").setReceptionistNumber("R002MN").build();
        String url = baseURL + "/update/";
        System.out.println("Appointment to update URL " + url);
        System.out.println("Updated appointment: "+ update);
        ResponseEntity<Appointment> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity(url, update, Appointment.class);
    }

    @Test
    @Disabled
    void f_delete(){
        String url = baseURL + "/delete/" + appointment.getAppointmentID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    void e_getAll(){
        String url = baseURL + "/getall";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, header);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .exchange(url, HttpMethod.GET, entity,String.class);
        System.out.println("Get Appointments:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}