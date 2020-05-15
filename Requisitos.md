# Divisão de tarefas de acordo com o requisito

### Story 1

Criação do esqueleto do projeto, contendo a criação da imagem Docker com acesso ao banco do dados.

- É necessário que a aplicação faça acesso ao banco de dados MongoDB, para armazenamento das cotações.
- É necessário que seja utilizado Java 8, Maven. 
- Que seja possível criar a imagem e subir o projeto apenas executando docker-compose up --build.
- Configuração para documentação da API no Swagger.

Nessa story deve ser possível subir o container contendo a aplicação e o banco de dados.
Deve ser possível também acessar a página do Swagger.

### Story 2

Criação de Endpoint REST para consulta da cotação do dólar em determinado dia.

- A data tem que ser uma data válida e não pode ser maior ou igual a hoje, pois o serviço somente retorna datas passadas.

Após o desenvolvimento a o swagger deve ser atualizado contendo o novo Endpoint.
### Story 3

Criação de camada para acesso a API externa REST.

- Deve ser possível enviar as requisições à API externa.
- Após receber a resposta, grava no banco de Dados

### Story 4

Integração entre o endpoint criado e a chamada à API

- Fazer a integração das partes, para que seja possível executar todo o fluxo
- Adicionar monitoramento e envio das métricas ao Prometheus e Grafana.

Deve ser possível executar todo o fluxo e ser capaz de verificar os dados de monitoramento.

### Story 5
Testes automatizados com cucumber

- Criar teste automatizado para integração contendo os principais fluxos.

## Author

Valdelar Martins