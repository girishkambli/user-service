package cdc;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.ing.userservice.UserServiceApplication;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(PactRunner.class)
@Provider("user_service")
@PactFolder("pacts")
public class UserServiceProviderTest {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 9001, "/api");

    private static ConfigurableWebApplicationContext application;

    @BeforeClass
    public static void start() {
        application = (ConfigurableWebApplicationContext)
            SpringApplication.run(UserServiceApplication.class);
    }

    @State("User GET")
    public void toGetState() {
    }

    @State("User GET - Not Found")
    public void toGetStateNotFound() {
    }

    @State("User GET - Unauthorized")
    public void toGetStateUnauthorised() {
    }

    @State("User PUT")
    public void toPutState() {
    }

    @State("Invalid User PUT")
    public void toInvalidPutState() {
    }

    @State("PUT User Forbidden")
    public void toPutStateForbidden() {
    }

}
