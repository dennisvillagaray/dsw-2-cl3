package denaror.com.cl3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
    title = "Person API",
    version = "1.0",
    description = "Documentation Person API v1.0",
    contact = @Contact(
        name = "dennis Villagaray",
        email = "dennis.villagarayg@gmail.com"
    )),
    servers = {
      @Server(url = "http://localhost:8080", description = "Local server"),
      @Server(url = "https://person-api-dennis.herokuapp.com", description = "Heroku server")
    }
)

public class OpenApiConfiguration {

}
