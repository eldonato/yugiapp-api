# Yu-Gi-App!
Projeto criado para manter o histórico das pontuações e resultados de torneios do Yu-Gi-Oh! CardGame

## Tecnologias utilizadas
### Banco de Dados
O banco de dados está utilizando a imagem do postgres:16-alpine
Por padrão a porta do postgres utilizada pela imagem docker é 5432
- Schema: yugiapp
- Usuario: api_yugiapp
- Senha: api_yugiapp@2024

### API
Maven está sendo utilizado como gerenciador de pacotes
API está utilizando o Java 22 com o Spring Boot 3.3.3

## Como buildar localmente
Será necessário ter instalado:
- Docker
- Java 22

1. Crie uma dois arquivos para a criptografia em src/main/resources
   - Chave privada: app.key
   - Chave Pública: pub.key
3. Na pasta raíz do projeto, execute o comando ```docker compose -f ./docker/docker-compose.yml up -d```
4. Compile o projeto com o maven e o execute os comandos
   - ```mvn clean package```
   -  ```java -jar ./target/yugiapp-0.0.1-SNAPSHOT.jar```
5. Será criado um usuário com role de admin padrão com login e senha admin

