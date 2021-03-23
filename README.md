# Aplicação de Cadastro de Pessoas

## Back-end

A aplicação, desenvolvida em Java, expõe uma API de cadastro, alteração, remoção e consulta de pessoas com as seguintes informações:
- Nome - obrigatório
- Sexo
- E-mail - não obrigatório, com validação caso preenchido
- Data de Nascimento - obrigatória, com validação
- Naturalidade
- Nacionalidade
- CPF - obrigatório, com validação (formato e não pode haver dois cadastros com mesmo cpf)

## Segurança

O acesso à aplicação está protegido por OAuth com um header de autorização via Basic.

## Documentação

A API está toda documentada e pode ser acessada através do link http://localhost:8080/swagger-ui.html após rodar a aplicação.

## Endpoints para acesso à entidade Pessoa

<a href="https://ibb.co/qntWnq3"><img src="https://i.ibb.co/N38m3Dd/image.png" alt="image" border="0" /></a>

## Endpoint para acesso ao código fonte

<a href="https://ibb.co/k6K9L9r"><img src="https://i.ibb.co/wrywnwb/image.png" alt="image" border="0" /></a>

## Acesso aos endpoints protegidos por OAuth

Para acessar os endpoints protegidos por OAuth, é necessário um bearer access token que pode ser gerado através do seguinte passo-a-passo no Postman :

1. Preparar o seguinte Request HTTP do tipo POST na URL localhost:8080/oauth/token

<a href="https://ibb.co/8d8y891"><img src="https://i.ibb.co/JykYkHD/image.png" alt="image" border="0" /></a>

2. Na aba Body, criar e preencher os campos "username", "password" e "grant_type" de acordo com a configuração exigida :

<a href="https://ibb.co/hBmt0fF"><img src="https://i.ibb.co/qNrP65Y/image.png" alt="image" border="0" /></a>

username : root, password : root, grant_type : password

3. Na aba Authorization, selecionar Basic Auth como header de autenticação e preencher os campos "Username" e "Password" com as credenciais "root" e "root" :

<a href="https://ibb.co/8BnvtbG"><img src="https://i.ibb.co/3s56QvV/image.png" alt="image" border="0" /></a>

4. Por fim, um response body com o bearer access token será gerado para acessar os endpoints protegidos :

<a href="https://ibb.co/JRVTwMs"><img src="https://i.ibb.co/1f1Ntwm/image.png" alt="image" border="0" /></a>

