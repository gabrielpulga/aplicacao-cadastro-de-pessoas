package aplicacao.cadastropessoas.unit;

import aplicacao.cadastropessoas.controller.PessoaController;
import aplicacao.cadastropessoas.model.Pessoa;
import aplicacao.cadastropessoas.repository.PessoaRepository;
import aplicacao.cadastropessoas.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PessoaRepository pessoaRepository;

    @Autowired
    public WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void criarPessoa_quandoPost() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test Nome");
        given(pessoaRepository.save(pessoa)).willReturn(pessoa);
        mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(pessoa)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(pessoa.getNome())));
    }

    @Test
    public void listarPessoas_quandoGet()
            throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test nome");
        List<Pessoa> allPessoas = Arrays.asList(pessoa);
        given(pessoaRepository
                .findAll())
                .willReturn(allPessoas);
        mockMvc.perform(get("/pessoas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is(pessoa.getNome())));
    }

    @Test
    public void deletarPessoa_quandoDelete() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test Nome");
        pessoa.setId(89L);
        doNothing().when(pessoaRepository).delete(pessoa);
        mockMvc.perform(delete("/pessoas/" + pessoa.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void buscarPessoa_quandoGet() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test Nome");
        pessoa.setId(89L);
        given(pessoaRepository.findById(pessoa.getId())).willReturn(java.util.Optional.of(pessoa));
        mockMvc.perform(get("/pessoas/" + pessoa.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nome", is(pessoa.getNome())));
    }

    @Test
    public void atualizarPessoa_quandoPut() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test Nome");
        pessoa.setId(89L);
        given(pessoaRepository.save(pessoa)).willReturn(pessoa);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/pessoas/" + pessoa.getId().toString())
                .content(mapper.writeValueAsString(pessoa))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nome", is(pessoa.getNome())));
    }
}
 */
