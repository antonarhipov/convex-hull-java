# Convex Hull API (Spring Boot)

A simple REST API that computes the convex hull for a set of 2D points. You POST a list of points, and it returns the points on the convex hull in counterclockwise order.

The project includes several classic convex hull algorithms (Jarvis March, Graham Scan, Quickhull, Andrew’s Monotone Chain, Chan’s Algorithm). The HTTP endpoint currently uses a basic Jarvis-March–style implementation provided in `me.arhan.aidemo.math.TheAlgorithm`.


## Stack and Tooling
- Language: Java 21
- Framework: Spring Boot 3.5.6 (Web, Validation, Test)
- Build tool / package manager: Maven
- Runtime DB: H2 (dependency present; not used by the API endpoint)
- Testing: JUnit 5 (Spring Boot Test)


## Project Functionality Overview
- POST /convex: Accepts a JSON array of points `{ x: int, y: int }` and returns the convex hull as a JSON array of the hull points.
- Internally, the endpoint uses `TheAlgorithm.run` (Jarvis March variant) from `me.arhan.aidemo.math` package. Other algorithm implementations are present under `me.arhan.aidemo.custom` but are not wired into the REST layer by default.


## API
- URL: `POST /convex`
- Request body (application/json):
  [
    { "x": 1, "y": 2 },
    { "x": 3, "y": 4 },
    { "x": 5, "y": 6 },
    { "x": 7, "y": 9 }
  ]
- Response body (application/json): a JSON array of points on the hull, for example:
  [
    { "x": 1, "y": 2 },
    { "x": 7, "y": 9 },
    { "x": 5, "y": 6 }
  ]

Example using curl:

curl -X POST http://localhost:8080/convex \
  -H "Content-Type: application/json" \
  -d '[ {"x":1,"y":2}, {"x":3,"y":4}, {"x":5,"y":6}, {"x":7,"y":9}, {"x":15,"y":3}, {"x":4,"y":2}, {"x":5,"y":11}, {"x":4,"y":8} ]'

Alternatively, you can use the provided `requests.http` file with IntelliJ IDEA / HTTP Client.


## Setup and Running
Prerequisites:
- Java 21 installed and on PATH (verify with `java -version`).
- Maven installed (verify with `mvn -v`).

Build:
- mvn clean package

Run (development):
- mvn spring-boot:run

Run (packaged JAR):
- java -jar target/convexhull-0.0.1-SNAPSHOT.jar

The app starts on port 8080 by default.


## Scripts and Useful Commands
- Build: `mvn clean package`
- Run dev: `mvn spring-boot:run`
- Run tests: `mvn test`
- Format/Checkstyle: not configured
- Run packaged jar: `java -jar target/convexhull-0.0.1-SNAPSHOT.jar`


## Environment Variables / Configuration
Spring Boot can be configured via environment variables or `application.properties`.
- Server port: set `server.port` in `src/main/resources/application.properties` or `SERVER_PORT` env var.
  - Example: `SERVER_PORT=9090 mvn spring-boot:run`
- Spring profile: `SPRING_PROFILES_ACTIVE`
- No project-specific env variables are required for basic usage.

`src/main/resources/application.properties` is currently empty.


## Tests
- Test framework: JUnit 5 with Spring Boot Test
- To run: `mvn test`
- Current tests: `contextLoads` sanity test in `src/test/java/me/arhan/aidemo/AidemoApplicationTests.java`


## Entry Points
- Java entry point: `me.arhan.aidemo.Application` (Spring Boot)
- HTTP entry point: `POST /convex` implemented in `me.arhan.aidemo.AlgorithmController`


## Project Structure
- pom.xml
- requests.http
- src/main/java
  - me/arhan/aidemo/Application.java (Spring Boot main)
  - me/arhan/aidemo/AlgorithmController.java (REST controller: POST /convex)
  - me/arhan/aidemo/math
    - Coordinate.java (DTO for x,y)
    - TheAlgorithm.java (Jarvis March–style convex hull used by the API)
  - me/arhan/aidemo/custom (alternative algorithm implementations)
    - Algorithm.java (interface)
    - Coordinate.java (internal model for custom algorithms)
    - GrahamScan.java
    - JarvisMarch.java
    - Quickhull.java
    - AndrewsMonotoneChain.java
    - Orientation.java
- src/main/resources/application.properties (empty)
- src/test/java/me/arhan/aidemo/AidemoApplicationTests.java

Note: There are two Coordinate classes: one under `me.arhan.aidemo.math` (used by the API) and another under `me.arhan.aidemo.custom` (used internally by custom algorithms).


## License
No license file is currently included. If you are the repository owner, consider adding a license (e.g., MIT, Apache-2.0) to clarify usage rights.
