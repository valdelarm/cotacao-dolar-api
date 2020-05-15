# Cotação do dolar API

Busca pela cotação do dólar no dia solicitado

- Os requisitos com a divisão das tarefas se encontram no arquivo Requisitos.md na raiz do projeto 

### Pré-requisitos

Você precisa ter o docker instalado em sua máquina

### Instalando

Entre na raiz do projeto e execute o seguinte comando:

sudo docker-compose up --build

Após o projeto ser executado acesse pela seguinte url para acessar o Swagger:
 
http://localhost:8080/swagger-ui.html#

## Rodando a API

Para executar insira uma data menor que a de hoje para consulta da cotação do dólar naquela data.

## Melhorias

- Monitoração com Prometheus e Grafana exibindo métricas da API, do DB e da infra
- Tracing (Jaeger)
- Testes automatizados efetivos com Cucumber

## Author

Valdelar Martins
