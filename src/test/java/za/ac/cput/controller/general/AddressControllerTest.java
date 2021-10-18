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
import za.ac.cput.factory.general.AddressFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest
{

    Address address1 = AddressFactory.build("10", "Albermarle", "hazendal","7764" );
    Address address2 = AddressFactory.build("73", "Grasmere", "Athlone","7764" );

    @Autowired
    private TestRestTemplate restTemplate;
    private final String addressURL = "http://localhost:8080/address";

    @Test
    void a_createAddress1(){
        String url = addressURL + "/create";
        ResponseEntity<Address> responseEntity = restTemplate.postForEntity(url, address1, Address.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        address1 = responseEntity.getBody();
        System.out.println("New Address: "+address1);
        assertEquals(address1.getAddressNumber(), responseEntity.getBody().getAddressNumber());
    }

    @Test
    void b_createAddress2(){
        String url = addressURL + "/create";
        ResponseEntity<Address> responseEntity = restTemplate.postForEntity(url, address2, Address.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        address2 = responseEntity.getBody();
        System.out.println("New Address: "+address2);
        assertEquals(address2.getAddressNumber(), responseEntity.getBody().getAddressNumber());
    }

    @Test
    void d_readAddress(){
        String url = addressURL + "/read/" +address2.getAddressNumber();
        System.out.println("Url used to read the address: " + url);
        ResponseEntity<Address> responseCreate = restTemplate.getForEntity(url, Address.class);
        assertEquals(address2.getAddressNumber(), responseCreate.getBody().getAddressNumber());

    }

    @Test
    void e_updateAddress(){
        Address updatedAddress = new Address.Builder().copy(address1).setHouseNumber("40").build();
        String url = addressURL + "/update";
        System.out.println("Url used to update the address: " + url);
        System.out.println("Updated Address: "+ updatedAddress);
        ResponseEntity<Address> responseUpdate = restTemplate.postForEntity(url, updatedAddress, Address.class);
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
