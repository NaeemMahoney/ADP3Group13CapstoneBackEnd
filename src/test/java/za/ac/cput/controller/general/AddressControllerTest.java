package za.ac.cput.controller.general;

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
import za.ac.cput.factory.general.AddressFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest
{

    private static Address address1 = AddressFactory.build("10", "Albermarle", "hazendal","7764" );
    private static Address address2 = AddressFactory.build("73", "Grasmere", "Athlone","7764" );

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String addressURL = "http://localhost:8080/address";

    private String username = "user";
    private String password = "password";

    @Test
    void a_createAddress1(){
        String url = addressURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Address> httpEntity = new HttpEntity<>(address1, httpHeaders);
        ResponseEntity<Address> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Address.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        address1 = responseEntity.getBody();
        System.out.println("New Address: "+address1);
        assertEquals(address1.getAddressNumber(), responseEntity.getBody().getAddressNumber());
    }

    @Test
    void b_createAddress2(){
        String url = addressURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Address> httpEntity = new HttpEntity<>(address1, httpHeaders);
        ResponseEntity<Address> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Address.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        address2 = responseEntity.getBody();
        System.out.println("New Address: "+address2);
        assertEquals(address2.getAddressNumber(), responseEntity.getBody().getAddressNumber());
    }

    @Test
    void d_readAddress(){
        Address i = null;
        String url = addressURL + "/read/" +address2.getAddressNumber();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Address> request = new HttpEntity<>(i, httpHeaders);
        System.out.println("Url used to read the address: " + url);
        ResponseEntity<Address> responseCreate = restTemplate.postForEntity(url, request, Address.class);
        assertEquals(address2.getAddressNumber(), responseCreate.getBody().getAddressNumber());

    }

    @Test
    void e_updateAddress(){
        Address updatedAddress = new Address.Builder().copy(address1).setHouseNumber("40").build();
        String url = addressURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Address> httpEntity = new HttpEntity<>(updatedAddress, httpHeaders);
        System.out.println("Url used to update the address: " + url);
        System.out.println("Updated Address: "+ updatedAddress);
        ResponseEntity<Address> responseUpdate = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Address.class);
        assertNotNull(responseUpdate.getBody());
    }

    @Test
    void f_deleteAddress(){
        String url = addressURL + "/delete" + address1.getAddressNumber();
        System.out.println("Url used to delete the address: " + url);
        restTemplate.delete(url);
    }

    @Test
    void g_getAllAddresses(){
        String url = addressURL + "/getall";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, header);
        ResponseEntity<String> responseGetAll = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("Get All Addresses");
        System.out.println(responseGetAll);
        System.out.println(responseGetAll.getBody());
    }
}
