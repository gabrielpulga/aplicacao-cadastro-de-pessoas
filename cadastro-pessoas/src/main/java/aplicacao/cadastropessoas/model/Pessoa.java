package aplicacao.cadastropessoas.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sexo;
    private String email;
    private String dataDeNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
}
