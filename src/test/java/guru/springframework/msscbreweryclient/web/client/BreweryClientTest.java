package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BreweryClientTest {
    @Autowired
    BreweryClient client;
    @Test
    void getBeerById() {

        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }
    @Test
    void savedBeerTest(){
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();

        URI uri = client.saveNewBeer(beerDto);
        assertNotNull(uri);

        System.out.println(uri);
    }
    @Test
    void updateBeerTest(){
        BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
        client.updateBeer(UUID.randomUUID(),beerDto);
    }
    @Test
    void deleteBeerTest(){
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {

        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {

        CustomerDto customerDto = CustomerDto.builder().name("Jhon Z").build();
        URI uri=  client.saveNewCustomer(customerDto);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateCustomerById() {
        CustomerDto customerDto = CustomerDto.builder().name("Yonatan").build();
        client.updateCustomerById(UUID.randomUUID(),customerDto);
    }

    @Test
    void deleteCustomerById() {

        client.deleteCustomerById(UUID.randomUUID());
    }
}