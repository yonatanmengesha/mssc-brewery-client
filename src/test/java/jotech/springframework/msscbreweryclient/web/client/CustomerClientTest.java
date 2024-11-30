package jotech.springframework.msscbreweryclient.web.client;

import jotech.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void getCustomerById() {

        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {

        CustomerDto customerDto = CustomerDto.builder().customerName("Jhon Z").build();
        URI uri=  client.saveNewCustomer(customerDto);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateCustomerById() {
        CustomerDto customerDto = CustomerDto.builder().customerName("Yonatan").build();
        client.updateCustomerById(UUID.randomUUID(),customerDto);
    }

    @Test
    void deleteCustomerById() {

        client.deleteCustomerById(UUID.randomUUID());
    }
}