package aplicacao.cadastropessoas.controller;

import aplicacao.cadastropessoas.model.Pessoa;
import aplicacao.cadastropessoas.repository.PessoaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Api(tags = "Pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @ApiOperation("Criar uma nova pessoa")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Pessoa> criar(@ApiParam(name = "corpo", value = "Representação de uma nova pessoa") @Validated @RequestBody Pessoa pessoa, HttpServletResponse httpServletResponse) {

        // Setar data de cadastro
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatarData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataAtual.format(formatarData);
        pessoa.setDataDeCadastro(dataAtual);

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaSalva.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @ApiOperation("Buscar uma pessoa pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Pessoa> buscar(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas")
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome")
    @GetMapping("/nome/{nome}")
    public List<Pessoa> listarPorNome(@PathVariable String nome) {
        return pessoaRepository.findByNomeContaining(nome);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome em ordem crescente")
    @GetMapping("/nome/asc")
    public List<Pessoa> listarPorNomeOrdemCrescente() {
        return pessoaRepository.findAllByOrderByNomeAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome em ordem decrescente")
    @GetMapping("/nome/desc")
    public List<Pessoa> listarPorNomeOrdemDecrescente() {
        return pessoaRepository.findAllByOrderByNomeDesc();
    }

    @ApiOperation("Deletar uma pessoa")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletar(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Pessoa pessoaDeletada = pessoaRepository.getOne(id);
        pessoaRepository.delete(pessoaDeletada);
    }

    @ApiOperation("Atualizar uma pessoa")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Pessoa> atualizar(@ApiParam(value = "ID", example = "1") @PathVariable Long
                                                    id, @ApiParam(name = "corpo", value = "Representação de uma nova pessoa") @Validated @RequestBody Pessoa
                                                    pessoa) {
        // Setar data de atualização
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatarData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataAtual.format(formatarData);
        pessoa.setDataDeAtualizacao(dataAtual);

        Pessoa pessoaSalva = pessoaRepository.getOne(id);

        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        pessoaRepository.save(pessoaSalva);
        return ResponseEntity.ok(pessoaSalva);
    }
}
