# Cotação do dolar API

Busca pela cotação do solicitado

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
