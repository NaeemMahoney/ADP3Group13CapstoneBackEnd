package za.ac.cput.controller.general;
/* AppointmentControllerTest.java
   Test class for controllers
   Author: Nolusindiso Makosa (219023557)
   Due Date: 20 October 2021
*/

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
    private static  Appointment appointment1 = AppointmentFactory.build("29 October", "13:00PM", "Fetch Results",
            "P10NM","D123MM", "R001JK" );

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String baseURL = "http://localhost:8080/appointment";

    private String username = "user";
    private String password = "password";

    @Test
    void a_create01() {
        String url = baseURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Appointment> httpEntity = new HttpEntity<>(appointment, httpHeaders);
        ResponseEntity<Appointment> responseEntity = restTemplate.exchange(url,HttpMethod.POST,httpEntity, Appointment.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        appointment = responseEntity.getBody();
        System.out.println("Added new Appointment " + appointment);
        assertEquals(appointment.getAppointmentID(), responseEntity.getBody().getAppointmentID());
    }

    @Test
    void b_create02() {
        String url = baseURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Appointment> httpEntity = new HttpEntity<>(appointment1, httpHeaders);
        ResponseEntity<Appointment> responseEntity = restTemplate.exchange(url,HttpMethod.POST,httpEntity, Appointment.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        appointment1 = responseEntity.getBody();
        System.out.println("Added new Appointment " + appointment1);
        assertEquals(appointment1.getAppointmentID(), responseEntity.getBody().getAppointmentID());
}


    @Test
    void c_read() {
        Appointment a = null;
        String url = baseURL + "/read/" +appointment1.getAppointmentID();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Appointment> request = new HttpEntity<>(a, httpHeaders);
        System.out.println("URL to read: " + url);
        System.out.println("Read new appointment: " + appointment1);
        ResponseEntity<Appointment> responseCreate = restTemplate.postForEntity(url, request, Appointment.class);
        assertNotNull(appointment1.getAppointmentID(), responseCreate.getBody().getAppointmentID());
}

    @Test
       void d_update() {
        Appointment update = new Appointment.Builder().copy(appointment).setDate("13 November 2021").setTime("13:00PM").build();
        String url = baseURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Appointment> httpEntity = new HttpEntity<>(update, httpHeaders);
        System.out.println("Url used to update appointment: " + url);
        System.out.println("Appointment updated: "+ update);
        ResponseEntity<Appointment> responseUpdate = restTemplate.exchange(url, HttpMethod.POST, httpEntity,Appointment.class);
        assertNotNull(responseUpdate.getBody());
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
        String url = baseURL + "/getAll/";
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