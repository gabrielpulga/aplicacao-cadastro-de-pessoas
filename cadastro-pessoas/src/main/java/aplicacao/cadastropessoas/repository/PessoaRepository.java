package aplicacao.cadastropessoas.repository;

import aplicacao.cadastropessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public List<Pessoa> findByNomeContaining(String nome);
    public List<Pessoa> findAllByOrderByNomeAsc();
    public List<Pessoa> findAllByOrderByNomeDesc();
}
