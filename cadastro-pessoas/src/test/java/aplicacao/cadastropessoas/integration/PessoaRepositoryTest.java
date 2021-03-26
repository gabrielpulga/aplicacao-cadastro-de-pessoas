package aplicacao.cadastropessoas.integration;

import static org.assertj.core.api.Assertions.assertThat;

import aplicacao.cadastropessoas.model.Pessoa;
import aplicacao.cadastropessoas.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
/**
@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository pessoaRepository;

     Verificação após inicializar a aplicação
     *  Conforme inserido no arquivo data.sql
     *  Devem existir 7 instâncias do objeto Pessoa criadas
     *
     *  ATENÇÃO - cada vez que a aplicação é iniciada, o banco de dados é recriado novamente
     *  Isso causa a repetição da inserção dos valores já presentes, fazendo o teste falhar ao ser rodado mais de 1x
     *
     *  SOLUÇÃO - "Drop Schema" após criar a tabela.

    @Test
    public void verificarExistencia_quandoCriada() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas.size()).isEqualTo(7);
    }
}
*/