package aplicacao.cadastropessoas.integration;

import static org.assertj.core.api.Assertions.assertThat;

import aplicacao.cadastropessoas.model.Pessoa;
import aplicacao.cadastropessoas.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository pessoaRepository;

    @Test
    public void verificarExistencia_quandoCriada() {
        Pessoa gabriel = new Pessoa();
        gabriel.setNome("Gabriel");
        gabriel.setCpf("110.349.579-88");
        gabriel.setDataDeNascimento(LocalDate.ofEpochDay(0));
        pessoaRepository.save(gabriel);

        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas.size()).isEqualTo(1);
    }
}
