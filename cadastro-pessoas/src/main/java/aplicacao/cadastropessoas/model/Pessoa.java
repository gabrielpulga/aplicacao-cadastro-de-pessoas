package aplicacao.cadastropessoas.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Nome - obrigatório
 * Sexo
 * E-mail - não obrigatório, deve ser validado caso preenchido
 * Data de Nascimento - obrigatório, deve ser validada
 * Naturalidade
 * Nacionalidade
 * CPF - obrigatório, deve ser validado (formato e não pode haver dois
 * cadastros com mesmo cpf)
 */

@Data
@ApiModel(value = "Pessoa", description = "Representa uma pessoa")
@Table(name = "pessoa")
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Favor preencher campo nome.")
    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Email(message = "Favor preencher um endereço e-mail válido.")
    private String email;

    @NotNull(message = "Favor preencher campo data de nascimento.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;

    private String naturalidade;
    private String nacionalidade;

    @NotNull
    @CPF(message = "Favor preencher um CPF válido.")
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataDeCadastro;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataDeAtualizacao;
}
