package aplicacao.cadastropessoas.integration;

import aplicacao.cadastropessoas.CadastroPessoasApplication;
import aplicacao.cadastropessoas.model.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**

@SpringBootTest(classes = CadastroPessoasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private void instanciarNovaPessoa() {
        Pessoa pessoaNumberFour = new Pessoa();
        pessoaNumberFour.setNome("Four");
        pessoaNumberFour.setId(4L);

        ResponseEntity<Pessoa> responseEntity = restTemplate
                .postForEntity(createURLWithPort("/pessoas"), pessoaNumberFour, Pessoa.class);
    }

    @Test
    public void listarPessoasTest() throws JSONException {

        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/pessoas"),
                HttpMethod.GET, httpEntity, String.class);


        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void deletarPessoaTest() throws JSONException {

        instanciarNovaPessoa();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/pessoas/4/"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertEquals(204, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(null, responseEntity.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
 */

