package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix ="sfg.brewery",ignoreUnknownFields = false)
public class CustomerClient {


    private  final String CUSTOMER_PATH_V1 ="/api/v1/customer/";
    @Setter
    private String apihost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public CustomerDto getCustomerById(UUID customerId){

        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1+ customerId.toString(), CustomerDto.class);
    }

    public URI  saveNewCustomer(CustomerDto customerDto){

        return restTemplate.postForLocation(apihost+CUSTOMER_PATH_V1,customerDto);
    }

    public void updateCustomerById(UUID customerId, CustomerDto customerDto){

        restTemplate.put(apihost+CUSTOMER_PATH_V1+ customerId.toString(),customerDto);
    }

    public void deleteCustomerById(UUID customerId){

        restTemplate.delete(apihost+CUSTOMER_PATH_V1+customerId);
    }

}
