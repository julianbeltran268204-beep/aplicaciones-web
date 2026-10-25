# Práctica 14 · Pokemon API REST

API REST con **Spring Boot + JPA + MySQL** para administrar Pokémon (CRUD completo).
Misma estructura por capas que GameVault: `model → repository → service → controller`.

## Cómo correrlo

1. Ten MySQL encendido en el puerto `3306`.
2. Ajusta usuario/contraseña en `src/main/resources/application.properties`.
   La base `pokemon_api_db` y la tabla `pokemones` se crean solas (`createDatabaseIfNotExist=true` + `ddl-auto=update`).
3. Ejecuta `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).
4. Prueba en Postman: `http://localhost:8080/api/pokemones`

## Endpoints

| Método | Ruta | Descripción | Respuesta |
|---|---|---|---|
| GET | `/api/pokemones` | Obtener todos | 200 |
| GET | `/api/pokemones/{id}` | Obtener uno | 200 / 404 |
| POST | `/api/pokemones` | Crear | 201 |
| PUT | `/api/pokemones/{id}` | Reemplazar todos los atributos | 200 / 404 |
| PATCH | `/api/pokemones/{id}` | Actualizar solo los atributos enviados | 200 / 404 |
| DELETE | `/api/pokemones/{id}` | Eliminar | 204 / 404 |

## Ejemplo de JSON

```json
{
  "nombre": "Pikachu",
  "tipo": "Eléctrico",
  "nivel": 5,
  "ataque": 55,
  "defensa": 40,
  "descripcion": "Ratón eléctrico que almacena energía en sus mejillas.",
  "imagenUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
}
```

PATCH (solo sube de nivel): `{ "nivel": 36 }`

## Estructura

```
src/main/java/com/example/PokemonAPI/
├── PokemonApiApplication.java
├── model/Pokemon.java
├── repository/PokemonRepository.java
├── service/PokemonService.java
└── controller/PokemonController.java
Postman/   ← capturas de las pruebas + colección importable
```
