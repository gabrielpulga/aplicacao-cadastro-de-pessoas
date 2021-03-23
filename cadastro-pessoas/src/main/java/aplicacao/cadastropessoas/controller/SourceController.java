package aplicacao.cadastropessoas.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Source")
@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping
    @ApiOperation("Acesso ao link do código fonte")
    public String getSourceCode() {
        return "https://github.com/gabrielpulga/aplicacao-cadastro-de-pessoas";
    }
}