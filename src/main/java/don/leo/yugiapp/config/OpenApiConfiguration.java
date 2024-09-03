package don.leo.yugiapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class OpenApiConfiguration {


    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080/api");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Leo Donato");
        myContact.setEmail("kqjut3@gmail.com");

        Info information = new Info()
                .title("API de Rastreamento de Pontuação de Yu-Gi-Oh!")
                .version("1.0")
                .description("Esta api expõe endpoints para manter a pontuação dos jogadores")
                .contact(myContact);

        return new OpenAPI()
                .info(information)
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("bearer-jwt", Arrays.asList("read", "write"))
                                .addList("bearer-key", Collections.emptyList())
                )
                .servers(List.of(server));
    }
}
