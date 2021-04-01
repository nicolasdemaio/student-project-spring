# Spring Boot API Rest - JPA MySQL

*by **Nicolas De Maio** (nicolasdemaio)*  
&nbsp;


Proyecto realizado para aprender sobre Spring Boot, JPA y protocolo HTTP.
&nbsp;
Es una API Rest que permite inscribir estudiantes, registrar materias junto a un profesor y matricular los estudiantes a estas.   


## Herramientas utilizadas:
- Java
- Spring Boot
- Unit Test + Test double (w/ Mockito)
- Lombok (para la creacion mediante anotaciones de getters, setters y constructores)
- MySQL (base de datos)
- Spring Data JPA (interaccion con la base de datos)
- Spring Mail Server (envio de mails automaticos tras inscribir a un alumno)
- Swagger2 (documentar la API)

&nbsp;

## Conexion a la base de datos
Agregar esto al final de application.properties

&nbsp;

`spring.jpa.hibernate.ddl-auto=create
&nbsp;
spring.datasource.url=jdbc:mysql://{YOUR_MYSQL_SERVER}:3306/{DATABASE_NAME}
&nbsp;
spring.datasource.username={YOUR_MYSQL_USERNAME}
&nbsp;
spring.datasource.password={YOUR_MYSQL_PASSWORD}
&nbsp;
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect`   
&nbsp;

## Swagger
Podes ver la documentacion de la API (ejecutandola) en http://localhost:8080/swagger-ui.html#/  
&nbsp;

![image](https://user-images.githubusercontent.com/71046657/113358408-1ffd6680-931c-11eb-904f-77bcb112c5f0.png)


