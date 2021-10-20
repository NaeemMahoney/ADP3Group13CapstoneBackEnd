package za.ac.cput.controller.medication;

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
import za.ac.cput.entity.medication.Item;
import za.ac.cput.factory.medication.ItemFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {
    private static Item item1 = ItemFactory.build("Gavascon", "Medicine", 80, 50);
    private static Item item2 = ItemFactory.build("Panado", "Pills", 60, 100);
    private static Item item3 = ItemFactory.build("E45", "Cream", 100, 40);

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private final String itemURL = "http://localhost:8080/item";

    private String username = "user";
    private String password = "password";

    //Create Item Test1
    @Test
    void a_createItem1(){
        String url = itemURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityCreate1 = new HttpEntity<>(item1, httpHeaders);
        ResponseEntity<Item> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntityCreate1, Item.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        item1 = responseEntity.getBody();
        System.out.println("New Item: "+item1);
        assertEquals(item1.getItemID(), responseEntity.getBody().getItemID());
    }

    //Create Item Test2
    @Test
    void b_createItem2(){
        String url = itemURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityCreate2 = new HttpEntity<>(item2, httpHeaders);
        ResponseEntity<Item> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntityCreate2, Item.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        item2 = responseEntity.getBody();
        System.out.println("New Item: "+item2);
        assertEquals(item2.getItemID(), responseEntity.getBody().getItemID());
    }

    //Create Item Test3
    @Test
    void c_createItem3(){
        String url = itemURL + "/create";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityCreate3 = new HttpEntity<>(item3, httpHeaders);
        ResponseEntity<Item> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntityCreate3, Item.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        item3 = responseEntity.getBody();
        System.out.println("New Item: "+item3);
        assertEquals(item3.getItemID(), responseEntity.getBody().getItemID());
    }

    //Item Read Test
    @Test
    void d_readItem(){
        Item i = null;
        String url = itemURL + "/read/" +item2.getItemID();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityRead = new HttpEntity<>(i, httpHeaders);
        System.out.println("Url used to read the item: " + url);
        ResponseEntity<Item> responseCreate = restTemplate.postForEntity(url, httpEntityRead, Item.class);
        assertEquals(item2.getItemID(), responseCreate.getBody().getItemID());

    }

    //Update Item Test
    @Test
    void e_updateItem(){
        Item updatedItem = new Item.Builder().copy(item1).itemPrice(70).builder();
        String url = itemURL + "/update";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<Item> httpEntityUpdate = new HttpEntity<>(updatedItem, httpHeaders);
        System.out.println("Url used to update the item: " + url);
        System.out.println("Updated Item: "+ updatedItem);
        ResponseEntity<Item> responseUpdate = restTemplate.exchange(url, HttpMethod.POST, httpEntityUpdate, Item.class);
        assertNotNull(responseUpdate.getBody());
    }

    //Delete Item Test
    @Test
    void f_deleteItem(){
        String url = itemURL + "/delete" + item1.getItemID();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<String> httpEntityDelete = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseDelete = restTemplate.exchange(url, HttpMethod.DELETE, httpEntityDelete, String.class);
        System.out.println("Url used to delete the item: " + url);
        restTemplate.delete(url);
    }

    //Get All Items Test
    @Test
    void g_getAllItems(){
        String url = itemURL + "/getall";
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<String> httpEntityUpdate = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseGetAll = restTemplate.exchange(url, HttpMethod.GET, httpEntityUpdate, String.class);
        System.out.println("Get All Items");
        System.out.println(responseGetAll);
        System.out.println(responseGetAll.getBody());
    }
}