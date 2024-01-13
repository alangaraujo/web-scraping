# Deprecated due to GitHub front-end page changes - For evaluating purposes only

# Web Scraping API

This API was intended to make a raw access to public repositories from a Git Provider (i.e GitHub) and collect information through front-end http file calling.

## How to run

### Docker

There is an Docker image available at DockerHub, to run, type at terminal:

> docker run -d -p 8080:8080 alangaraujo/web-scraping

### From IDE

From your favourite Java IDE editor, after pull this source code, import as a Maven Application (if it does), and run the main class as a Java Application, that can be found at br.com.alangaraujo.WebScrapingApplication package.

### Maven

From root folder of this project you can simply run from your terminal as:

> Linux: ./mvnw spring-boot:run

> Windows: mvnw.cmd spring-boot:run

### Run as Java -JAR

You must compile first from Maven, and then, run directly from Java, this project was build with Java 11.

> mvn clean package

> java -jar target/web-scraping.jar

## Accessing the API

### Postman

Just copy and past the URL below to Postman address and replace the keywords with Git provider name and repository, using a GET method, as follows:

> http://URL:8080/documents?provider=GIT_PROVIDER&repositoryUri=USER/REPOSITORY

Where:

>GIT_PROVIDER = The Git provider, for this project, only "github" is available.

>USER/REPOSITORY = Just like GitHub's URI, replace the user and repository, matching the pattern, like "alangaraujo/web-scraping".

### Swagger

Also, this project provides the API documentation Swagger, just hit this URL address after Spring's start (replacing the localhost address if you are not running this in your computer).

> http://localhost:8080/swagger-ui.html

## Caching

Due to response time after first request, this application uses caching, with timeout of 10 minutes.
