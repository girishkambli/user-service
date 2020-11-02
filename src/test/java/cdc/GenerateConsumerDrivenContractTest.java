package cdc;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.userservice.api.dto.Address;
import com.ing.userservice.api.dto.Gender;
import com.ing.userservice.api.dto.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GenerateConsumerDrivenContractTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("user_service", "localhost",
        getAvailablePort(), this);

    @Pact(consumer = "user_client")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return builder
            .given("User GET")
            .uponReceiving("GET REQUEST")
            .path("/users/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(headers)
            .body(this::validUser)

            .given("User PUT")
            .uponReceiving("PUT REQUEST")
            .method("PUT")
            .headers(headers)
            .body(this.updatedUser("1"))
            .path("/users/1")
            .willRespondWith()
            .status(200)

            .toPact();
    }

    @SneakyThrows
    private String validUser() {
        ObjectMapper mapper = new ObjectMapper();
        User userDetails = new User();
        userDetails.setId("1");
        userDetails.setFirstName("Girish");
        userDetails.setLastName("Kambli");
        userDetails.setTitle("Mr.");
        userDetails.setGender(Gender.MALE);
        Address address = new Address();
        address.setStreet("1 Martin Pl");
        address.setCity("Sydney");
        address.setState("NSW");
        address.setPostcode("2000");
        userDetails.setAddress(address);

        return mapper.writeValueAsString(userDetails);
    }

    @SneakyThrows
    private String updatedUser(String id) {
        ObjectMapper mapper = new ObjectMapper();
        User userDetails = new User();
        userDetails.setId(id);
        userDetails.setFirstName("Girish");
        userDetails.setLastName("Kambli");
        userDetails.setTitle("Mr.");
        userDetails.setGender(Gender.MALE);
        Address address = new Address();
        address.setStreet("1 Market St");
        address.setCity("Sydney");
        address.setState("NSW");
        address.setPostcode("2000");
        userDetails.setAddress(address);

        return mapper.writeValueAsString(userDetails);
    }

    @Ignore
    @Test
    @PactVerification()
    public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
        // when
        ResponseEntity<String> response = new RestTemplate()
            .getForEntity(mockProvider.getUrl() + "/users/1", String.class);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        // and
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = updatedUser("1");

        // when
        ResponseEntity<String> putResponse = new RestTemplate()
            .exchange(mockProvider.getUrl() + "/users/1", HttpMethod.PUT,
                new HttpEntity<>(jsonBody, httpHeaders), String.class);

        // then
        assertThat(putResponse.getStatusCode().value()).isEqualTo(200);
    }

    private static int getAvailablePort() {
        return new Random()
            .ints(6000, 9000)
            .filter(GenerateConsumerDrivenContractTest::isFree)
            .findFirst()
            .orElse(8080);
    }

    private static boolean isFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
