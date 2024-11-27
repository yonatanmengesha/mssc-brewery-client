package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {


    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnections;
    private final Integer connectionRequestTimeOut;
    private final Integer socketTimeOut;

    public BlockingRestTemplateCustomizer(  @Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
                                            @Value("${sfg.defaultmaxtotalconnections}") Integer defaultMaxTotalConnections,
                                            @Value("${sfg.connectionrequesttimeout}") Integer connectionRequestTimeOut,
                                            @Value("${sfg.sockettimeout}") Integer socketTimeOut) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnections = defaultMaxTotalConnections;
        this.connectionRequestTimeOut = connectionRequestTimeOut;
        this.socketTimeOut = socketTimeOut;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnections);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeOut)
                .setSocketTimeout(socketTimeOut).build();


        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig).build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
           restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
