package don.leo.yugiapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Leo Donato");
        myContact.setEmail("kqjut3@gmail.com");

        Info information = new Info()
                .title("API de Rastreamento de Pontuação de Yu-Gi-Oh!")
                .version("1.0")
                .description("Esta api expõe endpoints para manter a pontuação dos jogadores")
                .contact(myContact);

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
