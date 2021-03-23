package aplicacao.cadastropessoas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping
    public String getSourceCode() {
        return "https://github.com/gabrielpulga/aplicacao-cadastro-de-pessoas";
    }
}