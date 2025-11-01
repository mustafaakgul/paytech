# Run Local Application
* mvn spring-boot:run

# URLS
* http://localhost:8091/v3/api-docs
* http://localhost:8091/swagger-ui/index.html
* http://localhost:8091/swagger-ui.html
* http://localhost:8091/swagger-resources

# File Log Config (resources/logback-file.xml)
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
<appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>logs/app.log</file>
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
</appender>
<root level="INFO">
    <appender-ref ref="FILE" />
</root>
</configuration>

# STOUT Log Config (resources/logback-file.xml)
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg [traceId=%X{traceId}] [userId=%X{userId}]%n</pattern>
    </encoder>
</appender>
<root level="INFO">
    <appender-ref ref="STDOUT" />
</root>
</configuration>
