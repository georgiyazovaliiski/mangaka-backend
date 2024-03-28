# Mangaka World
This is the backend of an innovative mangaka publishing platform.

Different manga artists can publish their amateur/professional manga pieces.

Moreover, the artists themselves can pick soundtracks that would be playable for readers on demand.

Soundtrack addition to manga will enhance the readers experience. Readers will be able to feel, read and hear the piece exactly how the creator indended.

Remember to add the following folders and files
1) /resources
2) /resources/mangapages
3) /resources/songs
4) /resources/application.properties

Application properties file should have somewhat of a structure like this:

```java
spring.datasource.url=jdbc:mysql://localhost:3306/manga_world
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=30MB

manga.storage.location=C:/YOURUSER/mangaka-backend/mangakaworld/src/main/resources/mangapages
manga.storage.song.location=C:/YOURUSER/mangaka-backend/mangakaworld/src/main/resources/songs

# Drop and recreate the schema and tables on startup (careful, may result in data loss)        
spring.jpa.hibernate.ddl-auto=create
```
