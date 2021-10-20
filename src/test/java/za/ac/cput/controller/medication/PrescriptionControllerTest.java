package za.ac.cput.controller.medication;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.entity.medication.Prescription;
import za.ac.cput.factory.medication.PrescriptionFactory;
import za.ac.cput.services.medication.PrescriptionService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class PrescriptionControllerTest {
    private static Prescription prescription1 = PrescriptionFactory.buildPrescription("Bazali","15","79","16");
    private static Prescription prescription3 = PrescriptionFactory.buildPrescription("Lovey","3", "5", "4");
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String baseURL = "http://localhost:8080/prescription";


    private String username = "user";
    private String password = "password";

    @Test
    void a_create01() {
       String url = baseURL + "/create";
       httpHeaders.setBasicAuth(username, password);
        HttpEntity<Prescription> httpEntity = new HttpEntity<>(prescription1, httpHeaders);
        ResponseEntity<Prescription> responseEntity = restTemplate.exchange(url,HttpMethod.POST,httpEntity, Prescription.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        prescription1 = responseEntity.getBody();
        System.out.println("Saved Data " + prescription1);
        assertEquals(prescription1.getPrescriptionID(), responseEntity.getBody().getPrescriptionID());

    }
    @Test
    void b_create02() {
        String url = baseURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Prescription> httpEntity = new HttpEntity<>(prescription3, httpHeaders);
        ResponseEntity<Prescription> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Prescription.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        prescription3 = responseEntity.getBody();
        System.out.println("Saved Data " + prescription3);
        assertEquals(prescription3.getPrescriptionID(), responseEntity.getBody().getPrescriptionID());
    }


    @Test
    void c_read() {
        Prescription i = null;
        String url = baseURL + "/read/" +prescription3.getPrescriptionID();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Prescription> request = new HttpEntity<>(i, httpHeaders);
        System.out.println("Url  " + url);
        ResponseEntity<Prescription> responseCreate = restTemplate.postForEntity(url, request, Prescription.class);
        assertNotNull(prescription3.getPrescriptionID(), responseCreate.getBody().getPrescriptionID());
    }


    @Test
    void d_update(){
        Prescription update = new Prescription.Builder().copy(prescription1).setName("Baba Juniour").build();
        String url = baseURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Prescription> httpEntity = new HttpEntity<>(update, httpHeaders);
        System.out.println("Url used to update the item: " + url);
        System.out.println("Updated Item: "+ update);
        ResponseEntity<Prescription> responseUpdate = restTemplate.exchange(url, HttpMethod.POST, httpEntity,Prescription.class);
        assertNotNull(responseUpdate.getBody());
    }

   @Test
   void f_delete(){
        String url = baseURL + "/delete" + prescription1.getPrescriptionID();
       System.out.println("URL" + url);
       restTemplate.delete(url);
   }

   @Test
    void e_getAll(){
        String url = baseURL + "/getall";
       HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<>(null, headers);
       ResponseEntity<String> response = restTemplate
               //basic config
               .withBasicAuth("user","password")
               .exchange(url, HttpMethod.GET, entity,String.class);
       System.out.println("show the entries made");
       System.out.println(response);
       System.out.println(response.getBody());

   }

}

