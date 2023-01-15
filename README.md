# VM Bears Assignment

## Implementação

- NodeJS: `16.18.1`
- Angular: `15.1.0`
- Spring Boot: `2.7`
- Maven: `3.8.7 (embeded)`
- Docker: `4.15.0`

## Execução

### Database

Na raíz do repositório acesse a pasta `database` e execute:

```bash
docker-compose up
```

### Back-End

Na raíz do repositório acesse a pasta `backend` e execute:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Front-End

Na raíz do repositório acesse a pasta `frontend` e execute:

```bash
npm install
ng serve --open
```

Caso não tenha o `Angular CLI` instalado, execute:

```bash
npm install -g @angular/cli
```
