# Кардо     
Приложение КАРДО объединяет людей, интересующихся уличной культурой.   
[https://kardoaward.com](https://kardoaward.com)

## Сведения о команде

PdM Алексей Климов     
PM Наталия Полонская

Back - Java      
Евгений Ли @evglv     
Михаил Фуртов @maminkrasavchik44     

Front    
Антон Машков    
Арсений Муравьев    
    
QA   
Елена Макарова    
Кристина Жукова     
     
Дизайнеры    
Екатерина Цапилова    
Мария Крупина    
Александра Тушенцова    


## Swagger:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
Swagger спецификация в формате json: kardo_swagger.json
 
## Технологии
Сервис разработан с использованием следующих технологий:

- Java 17 (Amazon Corretto 17)    
- Maven
- Spring Boot 3
- Spring Security
- Lombok
- Hibernate
- JPA
- PostgreSQL
- Swagger

## Инструкция по сборке и запуску    
Проект создан с использованием Maven.    
Порядок сборки  Maven -> Lifecycle -> Package    
На сервере установлены Java 17 (Amazon Corretto 17) и База Данных (PostgreSQL)    
Приложение размещено и запущено на сервере (работает в фоновом режиме)    
Для запуска использовалась команда (переходим в директорию):    
nohup java -jar app_name.jar > app_name.log 2>&1 &      
(без использования SystemD менеджера системы и служб для Linux)    




 
