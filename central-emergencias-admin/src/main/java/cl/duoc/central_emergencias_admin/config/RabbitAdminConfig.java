
package cl.duoc.central_emergencias_admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RabbitAdminConfig {

    @Value("${rabbit.admin.url}")
    private String rabbitAdminUrl;

    @Value("${rabbit.admin.username}")
    private String username;

    @Value("${rabbit.admin.password}")
    private String password;

    @Bean
    public RestClient rabbitAdminRestClient() {

        return RestClient.builder()
                .baseUrl(rabbitAdminUrl)
                .defaultHeaders(headers ->
                        headers.setBasicAuth(username, password))
                .build();
    }
}
